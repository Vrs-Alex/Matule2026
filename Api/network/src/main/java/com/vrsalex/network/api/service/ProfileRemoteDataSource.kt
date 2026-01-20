package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.ProfileDataResponse

interface ProfileRemoteDataSource {

    suspend fun getProfileData(): NetworkResult<ProfileDataResponse>

}