package ru.clevertec.repository.book

import dto.book.BooksFilterDto
import dto.page.PageRequest
import model.entity.BookEntity
import ru.clevertec.dto.book.BookResponse

interface BookRepository {
    fun create(book: BookEntity.() -> Unit): BookEntity
    fun findAll(pageRequest: PageRequest): Pair<List<BookResponse>, Long>
    fun findById(id: Int): BookResponse?
    fun findByIsbn(isbn: String): BookResponse?
    fun update(id: Int, block: BookEntity.() -> Unit): BookEntity?
    fun softDelete(id: Int): Boolean
    fun findByAuthorId(authorId: Int): List<BookResponse>
    fun findByGenreId(genreId: Int): List<BookResponse>
    fun search(filter: BooksFilterDto, pageRequest: PageRequest): List<BookResponse>
}