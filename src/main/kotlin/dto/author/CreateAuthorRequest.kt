package dto.author

import java.time.LocalDate

data class CreateAuthorRequest(
    val firstName: String,
    val lastName: String,
    val biography: String?,
    val birthDate: LocalDate?,
)