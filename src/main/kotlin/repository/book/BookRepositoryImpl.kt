package ru.clevertec.repository.book

import dto.book.BooksFilterDto
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

    override fun findAll(offset: Int, limit: Int): List<BookResponse> = transaction {
        BookEntity.all().limit(limit).offset(offset.toLong()).map(BookMapper::toResponse)
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

    override fun search(filter: BooksFilterDto, offset: Int, limit: Int): List<BookResponse> = transaction {
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
        filter.yearFrom?.let { yf ->
            conditions += { Books.year greaterEq yf }
        }
        filter.yearTo?.let { yt ->
            conditions += { Books.year lessEq yt }
        }
        filter.hasCover?.let { hc ->
            conditions += { if (hc) Books.coverUrl.isNotNull() else Books.coverUrl.isNull() }
        }

        val query = if (conditions.isEmpty()) {
            BookEntity.all()
        } else {
            BookEntity.find { conditions.map { it(SqlExpressionBuilder) }.reduce { acc, op -> acc and op } }
        }
        query.limit(limit).offset(offset.toLong()).toList().map(BookMapper::toResponse)
    }
}