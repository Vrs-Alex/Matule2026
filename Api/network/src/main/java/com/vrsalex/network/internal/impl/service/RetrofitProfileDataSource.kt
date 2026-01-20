package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.ProfileDataResponse
import com.vrsalex.network.api.service.ProfileRemoteDataSource
import com.vrsalex.network.internal.api.ProfileApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitProfileDataSource @Inject constructor(
    private val profileApi: ProfileApi
): ProfileRemoteDataSource {
    override suspend fun getProfileData(): NetworkResult<ProfileDataResponse> =
        safeApiCall { profileApi.getProfileData() }
}