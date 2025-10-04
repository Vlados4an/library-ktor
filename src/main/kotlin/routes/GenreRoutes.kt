package routes

import dto.genre.CreateGenreRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import service.genre.GenreService

fun Route.genreRoutes() {
    val genreService by closestDI().instance<GenreService>()

    route("/api/v1/genres") {
        post {
            val req = call.receive<CreateGenreRequest>()
            genreService.createGenre(req)
            call.respond(HttpStatusCode.Created)
        }
        get {
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
            val genres = genreService.getGenres(page, size)
            call.respond(genres)
        }
        get("/{id}/books") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val books = genreService.getBooksByGenre(id)
            call.respond(books)
        }
    }
}