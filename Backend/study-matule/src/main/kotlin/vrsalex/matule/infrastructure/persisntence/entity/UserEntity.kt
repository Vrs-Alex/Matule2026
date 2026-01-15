package vrsalex.matule.infrastructure.persisntence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Entity
@Table(name = "app_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long? = null,

    @Column(name = "email", unique = true, nullable = false)
    var email: String = "",

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String = "",

    @Column(name = "firstname")
    var firstname: String = "",

    @Column(name = "lastname")
    var lastname: String = "",

    @Column(name = "patronymic")
    var patronymic: String = "",

    @Column(name = "date_birthday")
    var dateBirthday: String = "",

    @Column(name = "gender")
    var gender: String = "",

    @Column(name = "verified")
    var verified: Boolean = false,

    @Column(name = "phone")
    var phone: String = "",

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)