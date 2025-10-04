package model.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object Authors : IntIdTable("author", "author_id") {
    val firstName = varchar("first_name", 100)
    val lastName = varchar("last_name", 100)
    val biography = text("biography").nullable()
    val birthDate = date("birth_date").nullable()
}