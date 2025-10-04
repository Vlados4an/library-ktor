package ru.clevertec.repository.author

import model.entity.AuthorEntity
import org.jetbrains.exposed.sql.transactions.transaction
import repository.author.AuthorRepository

class AuthorRepositoryImpl : AuthorRepository {
    override fun create(author: AuthorEntity.() -> Unit): AuthorEntity = transaction {
        AuthorEntity.new { author(this) }
    }

    override fun findAll(offset: Int, limit: Int): List<AuthorEntity> = transaction {
        AuthorEntity.all().limit(limit).offset(offset.toLong()).toList()
    }
}