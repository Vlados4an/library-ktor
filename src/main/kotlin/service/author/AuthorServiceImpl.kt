package ru.clevertec.service.author

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import dto.page.PageRequest
import dto.page.PageResponse
import mapper.AuthorMapper
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

    override fun getAuthors(pageRequest: PageRequest): PageResponse<AuthorResponse> {
        val (authors, total) = authorRepository.findAll(pageRequest)
        return PageResponse(
            content = authors,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = total
        )
    }

    override fun getBooksByAuthor(authorId: Int): List<BookResponse> {
        return bookRepository.findByAuthorId(authorId)
    }
}