package ru.clevertec.repository.book

import model.entity.BookEntity

interface BookRepository {
    fun create(book: BookEntity.() -> Unit): BookEntity
    fun findAll(offset: Int, limit: Int): List<BookEntity>
    fun findById(id: Int): BookEntity?
    fun findByIsbn(isbn: String): BookEntity?
    fun update(id: Int, block: BookEntity.() -> Unit): BookEntity?
    fun softDelete(id: Int): Boolean
    fun findByAuthorId(authorId: Int): List<BookEntity>
    fun findByGenreId(genreId: Int): List<BookEntity>
    fun search(filter: BooksFilterDto, offset: Int, limit: Int): List<BookEntity>
}