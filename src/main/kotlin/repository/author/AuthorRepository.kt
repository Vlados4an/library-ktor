package repository.author

import dto.author.AuthorResponse
import model.entity.AuthorEntity

interface AuthorRepository {
    fun create(author: AuthorEntity.() -> Unit): AuthorEntity
    fun findAll(offset: Int, limit: Int): List<AuthorResponse>
}