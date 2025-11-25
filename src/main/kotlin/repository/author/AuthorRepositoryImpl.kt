package ru.clevertec.repository.author

import dto.author.AuthorResponse
import dto.page.PageRequest
import mapper.AuthorMapper
import model.entity.AuthorEntity
import org.jetbrains.exposed.sql.transactions.transaction
import repository.author.AuthorRepository

class AuthorRepositoryImpl : AuthorRepository {
    override fun create(author: AuthorEntity.() -> Unit): AuthorEntity = transaction {
        AuthorEntity.new { author(this) }
    }

    override fun findAll(pageRequest: PageRequest): Pair<List<AuthorResponse>, Long> = transaction {
        val total = AuthorEntity.all().count()
        val authors = AuthorEntity.all().limit(pageRequest.size).offset(pageRequest.offset)
            .map(AuthorMapper::toResponse)
        authors to total
    }
}