package com.vrsalex.matuleapp.presentation.feature.profile

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.domain.profile.ProfileRepository
import com.vrsalex.matuleapp.domain.setting.SettingRepository
import com.vrsalex.network.api.common.AuthObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.appmetrica.analytics.AppMetrica
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authObserver: AuthObserver,
    private val settingRepository: SettingRepository,
    @ApplicationContext private val context: Context
): ViewModel() {


    val state = combine(
        profileRepository.getShortProfileData(),
        settingRepository.getNotification()
    ){ profile, notification ->
        ProfileContract.State(
            firstName = profile.firstName,
            email = profile.email,
            isShowNotification = notification
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileContract.State()
    )

    private val _channel = Channel<ProfileContract.Effect>(capacity = Channel.BUFFERED)
    val channel = _channel.receiveAsFlow()


    fun onEvent(e: ProfileContract.Event){
        when(e) {
            is ProfileContract.Event.OnChangeNotification -> {
                AppMetrica.reportEvent("Отключено/включено уведомление")
                viewModelScope.launch {
                    settingRepository.changeNotification(e.b)
                }
            }
            ProfileContract.Event.OnLogout -> {
                viewModelScope.launch {
                    authObserver.logout()
                }
            }
            ProfileContract.Event.OnPrivacyPolicy -> {
                viewModelScope.launch {
//                    _channel.send(ProfileContract.Effect.OnPrivacyPolicy("https://google.com"))
                    val tempFile = File(context.cacheDir, "policy.pdf")
                    context.assets.open("policy.pdf").use { inputStream ->
                        tempFile.outputStream().use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }
                    val publicUri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", tempFile)
                    Log.e("MYAPP", publicUri.toString())
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(publicUri, "application/pdf")
                    }.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)

                }
            }
            ProfileContract.Event.OnTermsOfUse -> {
                viewModelScope.launch {
//                    _channel.send(ProfileContract.Effect.OnPrivacyPolicy("https://google.com"))
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            profileRepository.fetchAndSaveProfile()
        }
    }

}