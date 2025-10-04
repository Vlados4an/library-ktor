package ru.clevertec.dto.book

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
)