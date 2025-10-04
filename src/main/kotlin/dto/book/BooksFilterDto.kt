package dto.book

import kotlinx.serialization.Serializable

@Serializable
data class BooksFilterDto(
    val title: String?,
    val authorId: Int?,
    val genreId: Int?,
    val language: String?,
    val yearFrom: Int?,
    val yearTo: Int?,
    val hasCover: Boolean?
)