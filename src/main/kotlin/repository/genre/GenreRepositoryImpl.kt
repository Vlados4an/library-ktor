package ru.clevertec.repository.genre

import model.entity.GenreEntity
import org.jetbrains.exposed.sql.transactions.transaction
import repository.genre.GenreRepository

class GenreRepositoryImpl : GenreRepository {
    override fun create(genre: GenreEntity.() -> Unit): GenreEntity = transaction {
        GenreEntity.new { genre(this) }
    }

    override fun findAll(offset: Int, limit: Int): List<GenreEntity> = transaction {
        GenreEntity.all().limit(limit).offset(offset.toLong()).toList()
    }
}