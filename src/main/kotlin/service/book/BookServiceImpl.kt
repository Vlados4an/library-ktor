package ru.clevertec.service.book

import KafkaConfig
import dto.book.BooksFilterDto
import dto.page.PageRequest
import dto.page.PageResponse
import mapper.BookMapper
import model.entity.BookEntity
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import ru.clevertec.dto.kafka.BookEvent
import ru.clevertec.exception.EntityNotFoundException
import ru.clevertec.kafka.consumer.KafkaProducerService
import ru.clevertec.repository.book.BookRepository

class BookServiceImpl(private val repository: BookRepository, private val kafkaProducerService: KafkaProducerService, private val kafkaConfig: KafkaConfig) :
    BookService {

    override suspend fun createBook(req: CreateBookRequest) {
        val entity = BookMapper.toEntity(req)
        val book = repository.create(entity)

        val event = BookEvent.Created(
            bookId = book.id.value,
            isbn = req.isbn,
            title = req.title,
            authorId = req.authorId
        )

        kafkaProducerService.sendEvent(
            topic = kafkaConfig.topics.bookEvents,
            key = book.id.toString(),
            event = event
        )
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

    override suspend fun updateBook(id: Int, req: UpdateBookRequest): BookResponse? {
        val book = repository.update(id) { BookMapper.updateEntity(this, req) }
            ?.let(BookMapper::toResponse)

        val event = BookEvent.Updated(
            bookId = id,
            changes = buildMap {
                req.title?.let { put("title", it) }
                req.description?.let { put("description", it) }
            }
        )

        kafkaProducerService.sendEvent(
            topic = kafkaConfig.topics.bookEvents,
            key = id.toString(),
            event = event
        )

        return book
    }

    override suspend fun deleteBook(id: Int) {
        val exists = repository.softDelete(id)
        if (!exists) {
            throw EntityNotFoundException("Book with id=$id not found")
        }
        val event = BookEvent.Deleted(bookId = id)

        kafkaProducerService.sendEvent(
            topic = kafkaConfig.topics.bookEvents,
            key = id.toString(),
            event = event
        )
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