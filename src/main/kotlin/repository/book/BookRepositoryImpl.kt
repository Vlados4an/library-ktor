package ru.clevertec.repository.book

import model.entity.BookEntity
import org.jetbrains.exposed.sql.transactions.transaction
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.model.table.Books

class BookRepositoryImpl : BookRepository {

    override fun create(book: BookEntity.() -> Unit): BookEntity = transaction {
        BookEntity.Companion.new { book(this) }
    }

    override fun findAll(offset: Int, limit: Int): List<BookEntity> = transaction {
        BookEntity.Companion.all().limit(limit).offset(offset.toLong()).toList()
    }

    override fun findById(id: Int): BookEntity? = transaction {
        BookEntity.Companion.findById(id)
    }

    override fun findByIsbn(isbn: String): BookEntity? = transaction {
        BookEntity.Companion.find { Books.isbn eq isbn }.singleOrNull()
    }

    override fun findByAuthorId(authorId: Int): List<BookEntity> = transaction {
        BookEntity.Companion.find { Books.author eq authorId }.toList()
    }

    override fun findByGenreId(genreId: Int): List<BookEntity> = transaction {
        BookEntity.Companion.find { Books.genre eq genreId }.toList()
    }

    override fun update(id: Int, block: BookEntity.() -> Unit): BookEntity? = transaction {
        BookEntity.Companion.findById(id)?.apply(block)
    }

    override fun softDelete(id: Int): Boolean = transaction {
        BookEntity.Companion.findById(id)?.apply {
            isDeleted = true
        } != null
    }

    override fun search(filter: BooksFilterDto, offset: Int, limit: Int): List<BookEntity> = transaction {
        val conditions = mutableListOf<SqlExpressionBuilder.() -> Op<Boolean>>()

        filter.title?.let { t ->
            conditions += { Books.title like "%$t%" }
        }
        filter.author?.let { a ->
            conditions += { Books.author eq a }
        }
        filter.genre?.let { g ->
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
        query.limit(limit).offset(offset.toLong()).toList()
    }
}