package dto.genre

data class CreateGenreRequest(
    val name: String,
    val description: String?
)