package ru.clevertec.service.author

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import mapper.AuthorMapper
import mapper.BookMapper
import repository.author.AuthorRepository
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.repository.book.BookRepository
import service.author.AuthorService

class AuthorServiceImpl(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) : AuthorService {

    override fun createAuthor(req: CreateAuthorRequest) {
        authorRepository.create(AuthorMapper.toEntity(req))
    }

    override fun getAuthors(page: Int, size: Int): List<AuthorResponse> {
        val offset = (page - 1) * size
        return authorRepository.findAll(offset, size)
    }

    override fun getBooksByAuthor(authorId: Int): List<BookResponse> {
        return bookRepository.findByAuthorId(authorId)
    }
}