package mapper

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import model.entity.AuthorEntity

object AuthorMapper {
    fun toEntity(req: CreateAuthorRequest): AuthorEntity.() -> Unit = {
        firstName = req.firstName
        lastName = req.lastName
        biography = req.biography
        birthDate = req.birthDate
    }
    fun toResponse(entity: AuthorEntity): AuthorResponse = AuthorResponse(
        id = entity.id.value,
        firstName = entity.firstName,
        lastName = entity.lastName,
        biography = entity.biography,
        birthDate = entity.birthDate
    )
}