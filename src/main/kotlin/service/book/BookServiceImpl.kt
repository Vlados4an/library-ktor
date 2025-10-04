package ru.clevertec.service.book

import dto.book.BooksFilterDto
import dto.page.PageRequest
import dto.page.PageResponse
import mapper.BookMapper
import model.entity.BookEntity
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import ru.clevertec.exception.EntityNotFoundException
import ru.clevertec.repository.book.BookRepository

class BookServiceImpl(private val repository: BookRepository) : BookService {

    override fun createBook(req: CreateBookRequest) {
        val entity = BookMapper.toEntity(req)
        repository.create(entity)
    }

    override fun getBooks(pageRequest: PageRequest): PageResponse<BookResponse> {
        val (books, total) = repository.findAll(pageRequest)
        return PageResponse(
            content = books,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = total
        )
    }

    override fun getBook(id: Int): BookResponse =
        repository.findById(id) ?: throw EntityNotFoundException("Book with id=$id not found")

    override fun getBookByIsbn(isbn: String): BookResponse =
        repository.findByIsbn(isbn) ?: throw EntityNotFoundException("Book with isbn=$isbn not found")

    override fun updateBook(id: Int, req: UpdateBookRequest): BookResponse? {
        return repository.update(id) { BookMapper.updateEntity(this, req) }
            ?.let(BookMapper::toResponse)
    }

    override fun deleteBook(id: Int) {
        val exists = repository.softDelete(id)
        if (!exists) {
            throw EntityNotFoundException("Book with id=$id not found")
        }
    }

    override fun searchBooks(filter: BooksFilterDto, pageRequest: PageRequest): PageResponse<BookResponse> {
        val (books, total) = repository.findAll(pageRequest)
        return PageResponse(
            content = books,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = total
        )
    }

    override fun getRecommendations(id: Int): List<BookResponse> {
        val book = repository.findById(id) ?: return emptyList()
        return repository.findByGenreId(book.genreId)
            .filter { it.id != id }
            .take(5)
    }

    override fun uploadCover(id: Int, url: String): BookEntity? =
        repository.update(id) { coverUrl = url }
}