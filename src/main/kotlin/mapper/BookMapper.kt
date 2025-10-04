package mapper

import ru.clevertec.dto.book.BookResponse
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import model.entity.AuthorEntity
import model.entity.BookEntity
import model.entity.GenreEntity

object BookMapper {
    fun toEntity(req: CreateBookRequest): BookEntity.() -> Unit = {
        isbn = req.isbn
        title = req.title
        description = req.description
        author = AuthorEntity[req.authorId]
        genre = GenreEntity[req.genreId]
        publishedYear = req.publishedYear
        pageCount = req.pageCount
        language = req.language
        coverUrl = req.coverUrl
    }

    fun updateEntity(entity: BookEntity, req: UpdateBookRequest) {
        req.title?.let { entity.title = it }
        req.description?.let { entity.description = it }
        req.authorId?.let { entity.author = AuthorEntity[it] }
        req.genreId?.let { entity.genre = GenreEntity[it] }
        req.publishedYear?.let { entity.publishedYear = it }
        req.pageCount?.let { entity.pageCount = it }
        req.language?.let { entity.language = it }
        req.coverUrl?.let { entity.coverUrl = it }
    }

    fun toResponse(entity: BookEntity): BookResponse = BookResponse(
        id = entity.id.value,
        isbn = entity.isbn,
        title = entity.title,
        description = entity.description,
        genreId = entity.genre.id.value,
        genreName = entity.genre.name,
        author = "${entity.author.firstName} ${entity.author.lastName}",
        publishedYear = entity.publishedYear,
        pageCount = entity.pageCount,
        language = entity.language,
        coverUrl = entity.coverUrl,
        createdAt = entity.createdAt.toString(),
        updatedAt = entity.updatedAt.toString()
    )
}