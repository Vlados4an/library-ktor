package ru.clevertec.service.book

import dto.book.BooksFilterDto
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import model.entity.BookEntity

interface BookService {
    fun createBook(req: CreateBookRequest)
    fun getBooks(page: Int, size: Int): List<BookResponse>
    fun getBook(id: Int): BookResponse?
    fun getBookByIsbn(isbn: String): BookResponse?
    fun updateBook(id: Int, req: UpdateBookRequest): BookResponse?
    fun deleteBook(id: Int): Boolean
    fun uploadCover(id: Int, url: String): BookEntity?
    fun searchBooks(filter: BooksFilterDto, page: Int, size: Int): List<BookResponse>
    fun getRecommendations(id: Int): List<BookResponse>
}