package routes

import dto.book.BooksFilterDto
import ru.clevertec.dto.book.CreateBookRequest
import ru.clevertec.dto.book.UpdateBookRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import ru.clevertec.service.book.BookService

fun Route.bookRoutes() {
    val bookService by closestDI().instance<BookService>()

    route("/api/v1/books") {

        post {
            val req = call.receive<CreateBookRequest>()
            bookService.createBook(req)
            call.respond(HttpStatusCode.Created)
        }

        get {
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
            val books = bookService.getBooks(page, size)
            call.respond(books)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val book = bookService.getBook(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(book)
        }

        get("/isbn/{isbn}") {
            val isbn = call.parameters["isbn"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val book = bookService.getBookByIsbn(isbn) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(book)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest)
            val req = call.receive<UpdateBookRequest>()
            bookService.updateBook(id, req)
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (bookService.deleteBook(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }

        get("/search") {
            val filter = call.receive<BooksFilterDto>()
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
            val books = bookService.searchBooks(filter, page, size)
            call.respond(books)
        }

        post("/{id}/cover") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest)
            val url = call.receive<String>()
            bookService.uploadCover(id, url)
                ?: return@post call.respond(HttpStatusCode.NotFound)
            call.respond(HttpStatusCode.OK)
        }

        get("/{id}/recommendations") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val recs = bookService.getRecommendations(id)
            call.respond(recs)
        }
    }
}
