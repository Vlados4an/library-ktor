package repository.genre

import model.entity.GenreEntity

interface GenreRepository {
    fun create(genre: GenreEntity.() -> Unit): GenreEntity
    fun findAll(offset: Int, limit: Int): List<GenreEntity>
}