package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.mapper.toEntity
import vrsalex.matule.infrastructure.persisntence.repository.jpa.RefreshTokenJpaRepository
import vrsalex.matule.domain.model.RefreshToken
import vrsalex.matule.domain.port.repository.RefreshTokenRepository

@Repository
class RefreshTokenRepositoryImpl(
    private val jpaRepository: RefreshTokenJpaRepository
): RefreshTokenRepository {
    override fun save(refreshToken: RefreshToken): RefreshToken {
        return jpaRepository.save(refreshToken.toEntity()).toDomain()
    }

    override fun findById(id: String): RefreshToken? {
        return jpaRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByTokenHash(tokenHash: String): RefreshToken? {
        return jpaRepository.findByTokenHash(tokenHash)?.toDomain()
    }

    override fun delete(id: String) {
        jpaRepository.deleteById(id)
    }

}