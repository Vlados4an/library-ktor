package dto.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String,
    val description: String?
)