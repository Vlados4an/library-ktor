package ru.clevertec.dto.book

import kotlinx.serialization.Serializable
import org.valiktor.functions.*
import org.valiktor.validate
import validator.Validatable

@Serializable
data class CreateBookRequest(
    val isbn: String,
    val title: String,
    val description: String?,
    val genreId: Int,
    val authorId: Int,
    val publishedYear: Int?,
    val pageCount: Int?,
    val language: String,
    val coverUrl: String?
) : Validatable {
    override fun validate() {
        validate(this) {
            validate(CreateBookRequest::isbn).isNotBlank().hasSize(min = 5, max = 20).matches(Regex("^[A-Za-z0-9-]+$"))
            validate(CreateBookRequest::title).isNotBlank().hasSize(max = 255)
            validate(CreateBookRequest::description).hasSize(max = 10000)
            validate(CreateBookRequest::genreId).isGreaterThan(0)
            validate(CreateBookRequest::authorId).isGreaterThan(0)
            validate(CreateBookRequest::language).isNotBlank()
            validate(CreateBookRequest::publishedYear).isGreaterThanOrEqualTo(1500).isLessThanOrEqualTo(2100)
            validate(CreateBookRequest::pageCount).isGreaterThan(0)
            validate(CreateBookRequest::language).isNotBlank().hasSize(max = 50)
            validate(CreateBookRequest::coverUrl).hasSize(max = 500).isWebsite()
        }
    }
}