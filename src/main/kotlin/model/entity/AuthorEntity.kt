package model.entity

import model.table.Authors
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.clevertec.model.table.Books

class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AuthorEntity>(Authors)

    var firstName by Authors.firstName
    var lastName by Authors.lastName
    var biography by Authors.biography
    var birthDate by Authors.birthDate

    val books by BookEntity referrersOn Books.author
}