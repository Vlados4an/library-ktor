package model.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.clevertec.model.table.Books

class BookEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BookEntity>(Books)

    var isbn by Books.isbn
    var title by Books.title
    var description by Books.description
    var genre by GenreEntity referencedOn Books.genre
    var author by AuthorEntity referencedOn Books.author
    var publishedYear by Books.publishedYear
    var pageCount by Books.pageCount
    var language by Books.language
    var coverUrl by Books.coverUrl
    var isDeleted by Books.isDeleted
    var createdAt by Books.createdAt
    var updatedAt by Books.updatedAt
    var year by Books.year
}
