package ru.clevertec.repository.book

import dto.book.BooksFilterDto
import model.entity.BookEntity
import ru.clevertec.dto.book.BookResponse

interface BookRepository {
    fun create(book: BookEntity.() -> Unit): BookEntity
    fun findAll(offset: Int, limit: Int): List<BookResponse>
    fun findById(id: Int): BookResponse?
    fun findByIsbn(isbn: String): BookResponse?
    fun update(id: Int, block: BookEntity.() -> Unit): BookEntity?
    fun softDelete(id: Int): Boolean
    fun findByAuthorId(authorId: Int): List<BookResponse>
    fun findByGenreId(genreId: Int): List<BookResponse>
    fun search(filter: BooksFilterDto, offset: Int, limit: Int): List<BookResponse>
}