package ru.clevertec.dto.book

import kotlinx.serialization.Serializable
import org.valiktor.functions.*
import org.valiktor.validate
import validator.Validatable

@Serializable
data class UpdateBookRequest(
    val title: String?,
    val description: String?,
    val genreId: Int?,
    val authorId: Int?,
    val publishedYear: Int?,
    val pageCount: Int?,
    val language: String?,
    val coverUrl: String?
) : Validatable {
    override fun validate() {
        validate(this) {
            validate(UpdateBookRequest::title)
                .hasSize(max = 255)
            validate(UpdateBookRequest::description)
                .hasSize(max = 1000)
            validate(UpdateBookRequest::genreId)
                .isGreaterThan(0)
            validate(UpdateBookRequest::authorId)
                .isGreaterThan(0)
            validate(UpdateBookRequest::publishedYear)
                .isGreaterThanOrEqualTo(1500)
                .isLessThanOrEqualTo(2100)
            validate(UpdateBookRequest::pageCount)
                .isGreaterThan(0)
            validate(UpdateBookRequest::language)
                .hasSize(max = 50)
            validate(UpdateBookRequest::coverUrl)
                .hasSize(max = 500)
                .isWebsite()
        }
    }
}