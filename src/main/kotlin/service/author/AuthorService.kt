package service.author

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import ru.clevertec.dto.book.BookResponse

interface AuthorService {
    fun createAuthor(req: CreateAuthorRequest)
    fun getAuthors(page: Int, size: Int): List<AuthorResponse>
    fun getBooksByAuthor(authorId: Int): List<BookResponse>
}