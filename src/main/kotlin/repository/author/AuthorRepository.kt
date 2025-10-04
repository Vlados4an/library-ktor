package repository.author

import model.entity.AuthorEntity

interface AuthorRepository {
    fun create(author: AuthorEntity.() -> Unit): AuthorEntity
    fun findAll(offset: Int, limit: Int): List<AuthorEntity>
}