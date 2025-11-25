package ru.clevertec.service.genre

import dto.genre.CreateGenreRequest
import dto.genre.GenreResponse
import dto.page.PageRequest
import dto.page.PageResponse
import mapper.GenreMapper
import repository.genre.GenreRepository
import ru.clevertec.dto.book.BookResponse
import ru.clevertec.repository.book.BookRepository
import service.genre.GenreService

class GenreServiceImpl(
    private val bookRepository: BookRepository,
    private val genreRepository: GenreRepository
) : GenreService {

    override fun createGenre(req: CreateGenreRequest) {
        genreRepository.create(GenreMapper.toEntity(req))
    }

    override fun getGenres(pageRequest: PageRequest): PageResponse<GenreResponse> {
        val (genres, total)  = genreRepository.findAll(pageRequest)
        return PageResponse(
            content = genres,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = total
        )
    }

    override fun getBooksByGenre(genreId: Int): List<BookResponse> {
        return bookRepository.findByGenreId(genreId)
    }
}