package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.infrastructure.persisntence.entity.UserEntity
import vrsalex.matule.domain.model.User
import java.time.LocalDateTime

fun UserEntity.toDomain(): User = User(
    id = this.id,
    email = this.email,
    passwordHash = this.passwordHash,
    firstName = this.firstname,
    lastName = this.lastname,
    patronymic = this.patronymic,
    birthday = this.dateBirthday,
    gender = this.gender,
    phoneNum = this.phone,
    verified = this.verified
)

fun User.toEntity(): UserEntity = UserEntity(
    id = this.id,
    email = this.email,
    passwordHash = passwordHash,
    firstname = this.firstName,
    lastname = this.lastName,
    patronymic = this.patronymic,
    phone = this.phoneNum,
    dateBirthday = this.birthday,
    gender = this.gender,
    verified = this.verified,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)