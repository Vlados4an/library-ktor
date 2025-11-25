package service.author

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import dto.page.PageRequest
import dto.page.PageResponse
import ru.clevertec.dto.book.BookResponse

interface AuthorService {
    fun createAuthor(req: CreateAuthorRequest)
    fun getAuthors(pageRequest: PageRequest): PageResponse<AuthorResponse>
    fun getBooksByAuthor(authorId: Int): List<BookResponse>
}