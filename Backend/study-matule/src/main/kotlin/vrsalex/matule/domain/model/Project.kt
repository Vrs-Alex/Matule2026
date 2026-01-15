package vrsalex.matule.domain.model

import java.time.LocalDateTime

data class Project(
    val id: Long? = null,
    val name: String,
    val startDate: String,
    val endDate: String,
    val url: String,
    val type: ProjectType,
    val category: ProjectCategory,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun isCompleted(now: LocalDateTime): Boolean {
        return false
    }
}