package routes

import dto.genre.CreateGenreRequest
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import ru.clevertec.util.getPageRequest
import ru.clevertec.validator.validatedReceive
import service.genre.GenreService
import util.getIntParamOrBadRequest

fun Route.genreRoutes() {
    val genreService by closestDI().instance<GenreService>()

    route("/api/v1/genres") {
        post {
            val req = call.validatedReceive<CreateGenreRequest>()
            genreService.createGenre(req)
            call.respond(HttpStatusCode.Created)
        }
        get {
            val pageRequest = call.getPageRequest()
            val genres = genreService.getGenres(pageRequest)
            call.respond(genres)
        }
        get("/{id}/books") {
            val id = call.getIntParamOrBadRequest("id")
            val books = genreService.getBooksByGenre(id)
            call.respond(books)
        }
    }
}