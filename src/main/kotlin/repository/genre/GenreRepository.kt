package repository.genre

import dto.genre.GenreResponse
import dto.page.PageRequest
import model.entity.GenreEntity

interface GenreRepository {
    fun create(genre: GenreEntity.() -> Unit): GenreEntity
    fun findAll(pageRequest: PageRequest): Pair<List<GenreResponse>, Long>
}