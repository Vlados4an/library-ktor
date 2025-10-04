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
        return repository.findAll(offset = (page - 1) * size, limit = size)
            .map(BookMapper::toResponse)
    }


    override fun getBook(id: Int): BookResponse? =
        repository.findById(id)?.let(BookMapper::toResponse)

    override fun getBookByIsbn(isbn: String): BookResponse? =
        repository.findByIsbn(isbn)?.let(BookMapper::toResponse)

    override fun updateBook(id: Int, req: UpdateBookRequest): BookResponse? {
        return repository.update(id) { BookMapper.updateEntity(this, req) }
            ?.let(BookMapper::toResponse)
    }

    override fun deleteBook(id: Int): Boolean = repository.softDelete(id)

    override fun searchBooks(filter: BooksFilterDto, page: Int, size: Int): List<BookResponse> {
        val offset = (page - 1) * size
        return repository.search(filter, offset, size).map(BookMapper::toResponse)
    }

    override fun getRecommendations(id: Int): List<BookResponse> {
        val book = repository.findById(id) ?: return emptyList()
        val recommended = repository.findByGenreId(book.genre.id.value)
            .filter { it.id.value != id }
            .take(5)
        return recommended.map(BookMapper::toResponse)
    }

    override fun uploadCover(id: Int, url: String): BookEntity? =
        repository.update(id) { coverUrl = url }

//    fun getRecommendations(id: Int): List<BookEntity> {
//        // простая логика: найти книги того же жанра
//        val book = repository.findById(id) ?: return emptyList()
//        return repository.search(genre = book.genre.name)
//            .filter { it.id.value != book.id.value }
//            .take(5)
//    }
}