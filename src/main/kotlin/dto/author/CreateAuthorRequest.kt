package dto.author

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.valiktor.functions.hasSize
import org.valiktor.functions.isLessThanOrEqualTo
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import validator.Validatable
import java.time.LocalDate

@Serializable
data class CreateAuthorRequest(
    val firstName: String,
    val lastName: String,
    val biography: String?,
    @Contextual
    val birthDate: LocalDate?,
) : Validatable {
    override fun validate() {
        validate(this) {
            validate(CreateAuthorRequest::firstName)
                .isNotBlank()
                .hasSize(max = 100)
            validate(CreateAuthorRequest::lastName)
                .isNotBlank()
                .hasSize(max = 100)
            validate(CreateAuthorRequest::biography)
                .hasSize(max = 2000)
            validate(CreateAuthorRequest::birthDate)
                .isLessThanOrEqualTo(LocalDate.now())
        }
    }
}