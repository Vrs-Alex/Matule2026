package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.infrastructure.persisntence.entity.RefreshTokenEntity
import vrsalex.matule.domain.model.RefreshToken

fun RefreshTokenEntity.toDomain(): RefreshToken = RefreshToken(
    id = this.id,
    userId = this.userId,
    tokenHash = this.tokenHash,
    createdAt = this.createdAt
)

fun RefreshToken.toEntity(): RefreshTokenEntity = RefreshTokenEntity(
    id = this.id,
    userId = this.userId,
    tokenHash = this.tokenHash,
    createdAt = this.createdAt
)