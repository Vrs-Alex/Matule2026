package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.infrastructure.persisntence.entity.UserEntity

interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?
}