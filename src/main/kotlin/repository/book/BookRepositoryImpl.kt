package ru.clevertec.repository.book

import dto.book.BooksFilterDto
import dto.page.PageRequest
import mapper.BookMapper
import model.entity.BookEntity
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.model.table.Books

class BookRepositoryImpl : BookRepository {

    override fun create(book: BookEntity.() -> Unit): BookEntity = transaction {
        BookEntity.Companion.new { book(this) }
    }

    override fun findAll(pageRequest: PageRequest): Pair<List<BookResponse>, Long> = transaction {
        val total = BookEntity.all().count()
        val books =  BookEntity.all().limit(pageRequest.size).offset(pageRequest.offset)
            .map(BookMapper::toResponse)
        books to total
    }

    override fun findById(id: Int): BookResponse? = transaction {
        BookEntity.findById(id)?.let(BookMapper::toResponse)
    }

    override fun findByIsbn(isbn: String): BookResponse? = transaction {
        BookEntity.find { Books.isbn eq isbn }.singleOrNull()?.let(BookMapper::toResponse)
    }

    override fun findByAuthorId(authorId: Int): List<BookResponse> = transaction {
        BookEntity.find { Books.author eq authorId }.map(BookMapper::toResponse)
    }

    override fun findByGenreId(genreId: Int): List<BookResponse> = transaction {
        BookEntity.find { Books.genre eq genreId }.map(BookMapper::toResponse)
    }

    override fun update(id: Int, block: BookEntity.() -> Unit): BookEntity? = transaction {
        BookEntity.Companion.findById(id)?.apply(block)
    }

    override fun softDelete(id: Int): Boolean = transaction {
        BookEntity.Companion.findById(id)?.apply {
            isDeleted = true
        } != null
    }

    override fun search(filter: BooksFilterDto, pageRequest: PageRequest): List<BookResponse> = transaction {
        val conditions = mutableListOf<SqlExpressionBuilder.() -> Op<Boolean>>()

        filter.title?.let { t ->
            conditions += { Books.title like "%$t%" }
        }
        filter.authorId?.let { a ->
            conditions += { Books.author eq a }
        }
        filter.genreId?.let { g ->
            conditions += { Books.genre eq g }
        }
        filter.language?.let { l ->
            conditions += { Books.language eq l }
        }
        filter.hasCover?.let { hc ->
            conditions += { if (hc) Books.coverUrl.isNotNull() else Books.coverUrl.isNull() }
        }

        val query = if (conditions.isEmpty()) {
            BookEntity.all()
        } else {
            BookEntity.find { conditions.map { it(SqlExpressionBuilder) }.reduce { acc, op -> acc and op } }
        }
        query.limit(pageRequest.size).offset(pageRequest.offset).toList().map(BookMapper::toResponse)
    }
}