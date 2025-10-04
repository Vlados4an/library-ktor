package mapper

import dto.genre.CreateGenreRequest
import dto.genre.GenreResponse
import model.entity.GenreEntity

object GenreMapper {
    fun toEntity(req: CreateGenreRequest): GenreEntity.() -> Unit = {
        name = req.name
        description = req.description
    }
    fun toResponse(entity: GenreEntity): GenreResponse = GenreResponse(
        id = entity.id.value,
        name = entity.name,
        description = entity.description
    )
}