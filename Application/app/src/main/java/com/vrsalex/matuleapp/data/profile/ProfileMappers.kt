package com.vrsalex.matuleapp.data.profile

import com.vrsalex.matuleapp.data.local.db.entity.UserEntity
import com.vrsalex.matuleapp.domain.profile.FullProfile
import com.vrsalex.network.api.dto.response.ProfileDataResponse

fun ProfileDataResponse.toFullProfile() = FullProfile(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    patronymic = this.patronymic,
    email = this.email,
    birthday = this.birthday,
    gender = this.gender
)


fun FullProfile.toEntity() = UserEntity(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    patronymic = this.patronymic,
    birthday = this.birthday,
    gender = this.gender,
    email = this.email
)