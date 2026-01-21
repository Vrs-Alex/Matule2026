package com.vrsalex.matuleapp.presentation.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vrsalex.matuleapp.domain.profile.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel() {


    val state = combine(
        profileRepository.getProfileData(),
        profileRepository.getNotification()
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
                viewModelScope.launch {
                    profileRepository.changeNotification(e.b)
                }
            }
            ProfileContract.Event.OnLogout -> {
                viewModelScope.launch {
                    profileRepository.logout()
                }
            }
            ProfileContract.Event.OnPrivacyPolicy -> {
                viewModelScope.launch {
                    _channel.send(ProfileContract.Effect.OnPrivacyPolicy("https://google.com"))
                }
            }
            ProfileContract.Event.OnTermsOfUse -> {
                viewModelScope.launch {
                    _channel.send(ProfileContract.Effect.OnPrivacyPolicy("https://google.com"))
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