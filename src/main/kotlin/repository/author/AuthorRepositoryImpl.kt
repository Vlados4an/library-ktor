package ru.clevertec.repository.author

import dto.author.AuthorResponse
import mapper.AuthorMapper
import model.entity.AuthorEntity
import org.jetbrains.exposed.sql.transactions.transaction
import repository.author.AuthorRepository

class AuthorRepositoryImpl : AuthorRepository {
    override fun create(author: AuthorEntity.() -> Unit): AuthorEntity = transaction {
        AuthorEntity.new { author(this) }
    }

    override fun findAll(offset: Int, limit: Int): List<AuthorResponse> = transaction {
        AuthorEntity.all().limit(limit).offset(offset.toLong()).toList().map(AuthorMapper::toResponse)
    }
}