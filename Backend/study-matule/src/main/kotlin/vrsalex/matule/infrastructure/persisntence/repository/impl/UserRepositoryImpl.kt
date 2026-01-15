package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.mapper.toEntity
import vrsalex.matule.infrastructure.persisntence.repository.jpa.UserJpaRepository
import vrsalex.matule.domain.model.User
import vrsalex.matule.domain.port.repository.UserRepository

@Repository
class UserRepositoryImpl(
    private val jpaRepository: UserJpaRepository
) : UserRepository {

    override fun save(user: User): User {
        val userEntity = jpaRepository.save(user.toEntity())
        return userEntity.toDomain()
    }

    override fun findById(id: Long): User? {
        return jpaRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByEmail(email: String): User? {
        return jpaRepository.findByEmail(email)?.toDomain()
    }

    override fun delete(id: Long) {
        jpaRepository.deleteById(id)
    }


}