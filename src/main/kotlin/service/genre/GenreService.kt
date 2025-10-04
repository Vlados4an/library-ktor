package service.genre

import dto.genre.CreateGenreRequest
import dto.genre.GenreResponse
import ru.clevertec.dto.book.BookResponse

interface GenreService {
    fun createGenre(req: CreateGenreRequest)
    fun getGenres(page: Int, size: Int): List<GenreResponse>
    fun getBooksByGenre(genreId: Int): List<BookResponse>
}