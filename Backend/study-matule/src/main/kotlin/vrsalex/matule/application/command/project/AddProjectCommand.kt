package vrsalex.matule.application.command.project

data class AddProjectCommand(
    val id: Long?,
    val name: String,
    val startDate: String,
    val endDate: String,
    val url: String,
    val type: String,
    val category: String,
    val userId: Long
)