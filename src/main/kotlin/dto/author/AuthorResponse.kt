package dto.author

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class AuthorResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val biography: String?,
    @Contextual
    val birthDate: LocalDate?,
)