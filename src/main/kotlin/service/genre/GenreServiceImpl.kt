package ru.clevertec.service.genre

import dto.genre.CreateGenreRequest
import dto.genre.GenreResponse
import mapper.BookMapper
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

    override fun getGenres(page: Int, size: Int): List<GenreResponse> {
        val offset = (page - 1) * size
        return genreRepository.findAll(offset, size)
            .map(GenreMapper::toResponse)
    }

    override fun getBooksByGenre(genreId: Int): List<BookResponse> {
        return bookRepository.findByGenreId(genreId)
            .map(BookMapper::toResponse)
    }
}