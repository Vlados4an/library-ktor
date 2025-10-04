package model.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.clevertec.model.table.Books
import ru.clevertec.model.table.Genres

class GenreEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GenreEntity>(Genres)

    var name by Genres.name
    var description by Genres.description

    val books by BookEntity referrersOn Books.genre
}