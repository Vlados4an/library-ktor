package ru.clevertec.routes

import dto.author.CreateAuthorRequest
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import ru.clevertec.util.getPageRequest
import ru.clevertec.validator.validatedReceive
import service.author.AuthorService
import util.getIntParamOrBadRequest

fun Route.authorRoutes() {
    val authorService by closestDI().instance<AuthorService>()

    route("/api/v1/authors") {
        post {
            val req = call.validatedReceive<CreateAuthorRequest>()
            authorService.createAuthor(req)
            call.respond(HttpStatusCode.Created)
        }
        get {
            val pageRequest = call.getPageRequest()
            val authors = authorService.getAuthors(pageRequest)
            call.respond(authors)
        }
        get("/{id}/books") {
            val id = call.getIntParamOrBadRequest("id")
            val books = authorService.getBooksByAuthor(id)
            call.respond(books)
        }
    }
}