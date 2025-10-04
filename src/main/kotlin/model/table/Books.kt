package ru.clevertec.model.table

import model.table.Authors
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.timestamp

object Books : IntIdTable(name = "book", columnName = "book_id") {
    val isbn = varchar("isbn", 50)
    val title = varchar("title", 255)
    val description = text("description").nullable()
    val genre = reference("genre_id", Genres)
    val author = reference("author_id", Authors)
    val publishedYear = integer("published_year").nullable()
    val pageCount = integer("page_count").nullable()
    val language = varchar("language", 50)
    val coverUrl = varchar("cover_url", 500).nullable()
    val isDeleted = bool("is_deleted").default(false)
    val year = integer("year").nullable()
    val createdAt = timestamp("created_at").defaultExpression(CurrentTimestamp)
    val updatedAt = timestamp("updated_at").defaultExpression(CurrentTimestamp)
}