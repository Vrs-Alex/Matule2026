package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.infrastructure.persisntence.entity.RefreshTokenEntity

interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenEntity, String>{

    fun findByTokenHash(tokenHash: String): RefreshTokenEntity?

}