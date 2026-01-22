package com.vrsalex.matuleapp.data.profile

import android.util.Log
import com.vrsalex.matuleapp.data.auth.AuthObserverImpl
import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.matuleapp.data.local.db.dao.UserDao
import com.vrsalex.matuleapp.domain.profile.FullProfile
import com.vrsalex.matuleapp.domain.profile.ProfileRepository
import com.vrsalex.matuleapp.domain.profile.ShortProfile
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.ProfileDataResponse
import com.vrsalex.network.api.service.ProfileRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val userDao: UserDao
): ProfileRepository {

    override fun getShortProfileData(): Flow<ShortProfile> {
        return userDao.getUserData().map { ShortProfile(it?.firstName ?: "", it?.email ?: "") }
    }

    override suspend fun fetchAndSaveProfile() {
        when(val remoteUser = profileRemoteDataSource.getProfileData()) {
            is NetworkResult.Success<ProfileDataResponse> -> {
                userDao.insert(remoteUser.data.toFullProfile().toEntity())
            }
            else -> {
                Log.e("MYAPP", remoteUser.toString())
            }
        }
    }
}