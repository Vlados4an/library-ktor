package mapper

import dto.author.AuthorResponse
import dto.author.CreateAuthorRequest
import model.entity.AuthorEntity

object AuthorMapper {
    fun toEntity(req: CreateAuthorRequest): AuthorEntity.() -> Unit = {
        firstName = req.firstName
        lastName = req.lastName
    }
    fun toResponse(entity: AuthorEntity): AuthorResponse = AuthorResponse(
        id = entity.id.value,
        firstName = entity.firstName,
        lastName = entity.lastName
    )
}