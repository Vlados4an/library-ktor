package repository.author

import dto.author.AuthorResponse
import dto.page.PageRequest
import model.entity.AuthorEntity

interface AuthorRepository {
    fun create(author: AuthorEntity.() -> Unit): AuthorEntity
    fun findAll(pageRequest: PageRequest): Pair<List<AuthorResponse>, Long>
}