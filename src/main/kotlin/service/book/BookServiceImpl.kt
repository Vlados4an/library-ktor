package ru.clevertec.service.book

import dto.book.BooksFilterDto
import mapper.BookMapper
import model.entity.BookEntity
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import ru.clevertec.repository.book.BookRepository

class BookServiceImpl(private val repository: BookRepository) : BookService {

    override fun createBook(req: CreateBookRequest) {
        val entity = BookMapper.toEntity(req)
        repository.create(entity)
    }

    override fun getBooks(page: Int, size: Int): List<BookResponse> {
        val offset = (page - 1) * size
        return repository.findAll(offset, size)
    }


    override fun getBook(id: Int): BookResponse? = repository.findById(id)

    override fun getBookByIsbn(isbn: String): BookResponse? = repository.findByIsbn(isbn)

    override fun updateBook(id: Int, req: UpdateBookRequest): BookResponse? {
        return repository.update(id) { BookMapper.updateEntity(this, req) }
            ?.let(BookMapper::toResponse)
    }

    override fun deleteBook(id: Int): Boolean = repository.softDelete(id)

    override fun searchBooks(filter: BooksFilterDto, page: Int, size: Int): List<BookResponse> {
        val offset = (page - 1) * size
        return repository.search(filter, offset, size)
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