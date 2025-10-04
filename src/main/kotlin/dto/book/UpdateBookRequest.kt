package ru.clevertec.dto.book

data class UpdateBookRequest(
    val title: String?,
    val description: String?,
    val genreId: Int?,
    val authorId: Int?,
    val publishedYear: Int?,
    val pageCount: Int?,
    val language: String?,
    val coverUrl: String?
)