package dto.genre

import kotlinx.serialization.Serializable
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import validator.Validatable

@Serializable
data class CreateGenreRequest(
    val name: String,
    val description: String?
) : Validatable {
    override fun validate() {
        validate(this) {
            validate(CreateGenreRequest::name)
                .isNotBlank()
                .hasSize(max = 100)
            validate(CreateGenreRequest::description)
                .hasSize(max = 1000)
        }
    }
}