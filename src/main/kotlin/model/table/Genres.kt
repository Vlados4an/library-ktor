package ru.clevertec.model.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Genres : IntIdTable("genre", "genre_id") {
    val name = varchar("name", 100)
    val description = text("description").nullable()
}