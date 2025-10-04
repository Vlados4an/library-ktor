package ru.clevertec.routes

import dto.author.CreateAuthorRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import service.author.AuthorService

fun Route.authorRoutes() {
    val authorService by closestDI().instance<AuthorService>()

    route("/api/v1/authors") {
        post {
            val req = call.receive<CreateAuthorRequest>()
            authorService.createAuthor(req)
            call.respond(HttpStatusCode.Created)
        }
        get {
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
            val authors = authorService.getAuthors(page, size)
            call.respond(authors)
        }
        get("/{id}/books") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val books = authorService.getBooksByAuthor(id)
            call.respond(books)
        }
    }
}