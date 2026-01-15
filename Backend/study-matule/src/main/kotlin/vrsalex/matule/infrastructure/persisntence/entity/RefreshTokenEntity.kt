package vrsalex.matule.infrastructure.persisntence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "refresh_token")
data class RefreshTokenEntity(
    @Id
    @Column(name = "token_id")
    var id: String? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0L,

    @Column(name = "token_hash", nullable = false, length = 512)
    var tokenHash: String = "",

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false, foreignKey = ForeignKey(name = "fk_refresh_token_user"))
    var user: UserEntity? = null
)