package service.genre

import dto.genre.CreateGenreRequest
import dto.genre.GenreResponse
import dto.page.PageRequest
import dto.page.PageResponse
import ru.clevertec.dto.book.BookResponse

interface GenreService {
    fun createGenre(req: CreateGenreRequest)
    fun getGenres(pageRequest: PageRequest): PageResponse<GenreResponse>
    fun getBooksByGenre(genreId: Int): List<BookResponse>
}