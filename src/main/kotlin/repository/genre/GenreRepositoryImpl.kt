package ru.clevertec.repository.genre

import dto.genre.GenreResponse
import dto.page.PageRequest
import mapper.GenreMapper
import model.entity.GenreEntity
import org.jetbrains.exposed.sql.transactions.transaction
import repository.genre.GenreRepository

class GenreRepositoryImpl : GenreRepository {
    override fun create(genre: GenreEntity.() -> Unit): GenreEntity = transaction {
        GenreEntity.new { genre(this) }
    }

    override fun findAll(pageRequest: PageRequest): Pair<List<GenreResponse>, Long> = transaction {
        val total = GenreEntity.all().count()
        val genres = GenreEntity.all().limit(pageRequest.size).offset(pageRequest.offset).toList().map(GenreMapper::toResponse)
        genres to total
    }
}