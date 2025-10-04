package ru.clevertec.dto.book

data class BookResponse(
    val id: Int,
    val isbn: String,
    val title: String,
    val description: String?,
    val genre: String,
    val author: String,
    val publishedYear: Int?,
    val pageCount: Int?,
    val language: String,
    val coverUrl: String?,
    val createdAt: String,
    val updatedAt: String
)