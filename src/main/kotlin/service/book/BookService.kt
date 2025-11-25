package ru.clevertec.service.book

import dto.book.BooksFilterDto
import dto.page.PageRequest
import dto.page.PageResponse
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import model.entity.BookEntity

interface BookService {
    suspend fun createBook(req: CreateBookRequest)
    fun getBooks(pageRequest: PageRequest): PageResponse<BookResponse>
    fun getBook(id: Int): BookResponse
    fun getBookByIsbn(isbn: String): BookResponse
    suspend fun updateBook(id: Int, req: UpdateBookRequest): BookResponse?
    suspend fun deleteBook(id: Int)
    fun uploadCover(id: Int, url: String): BookEntity?
    fun searchBooks(filter: BooksFilterDto, pageRequest: PageRequest): PageResponse<BookResponse>
    fun getRecommendations(id: Int): List<BookResponse>
}