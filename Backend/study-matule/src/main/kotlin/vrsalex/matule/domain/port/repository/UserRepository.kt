package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.User


interface UserRepository {
    fun save(user: User): User
    fun findById(id: Long): User?
    fun findByEmail(email: String): User?
    fun delete(id: Long)
}