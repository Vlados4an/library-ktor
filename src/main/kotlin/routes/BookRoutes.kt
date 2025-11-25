package routes

import ru.clevertec.util.getPageRequest
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
import ru.clevertec.validator.validatedReceive
import util.getIntParamOrBadRequest

fun Route.bookRoutes() {
    val bookService by closestDI().instance<BookService>()

    route("/api/v1/books") {

        post {
            val req = call.validatedReceive<CreateBookRequest>()
            bookService.createBook(req)
            call.respond(HttpStatusCode.Created)
        }

        get {
            val pageRequest = call.getPageRequest()
            val books = bookService.getBooks(pageRequest)
            call.respond(books)
        }

        get("/{id}") {
            val id = call.getIntParamOrBadRequest("id")
            val book = bookService.getBook(id)
            call.respond(book)
        }

        get("/isbn/{isbn}") {
            val isbn = call.parameters["isbn"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val book = bookService.getBookByIsbn(isbn)
            call.respond(book)
        }

        put("/{id}") {
            val id = call.getIntParamOrBadRequest("id")
            val req = call.validatedReceive<UpdateBookRequest>()
            bookService.updateBook(id, req)
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.getIntParamOrBadRequest("id")
            bookService.deleteBook(id)
            call.respond(HttpStatusCode.NoContent)
        }

        get("/search") {
            val filter = call.validatedReceive<BooksFilterDto>()
            val pageRequest = call.getPageRequest()
            val books = bookService.searchBooks(filter, pageRequest)
            call.respond(books)
        }

        post("/{id}/cover") {
            val id = call.getIntParamOrBadRequest("id")
            val url = call.receive<String>()
            bookService.uploadCover(id, url)
                ?: return@post call.respond(HttpStatusCode.NotFound)
            call.respond(HttpStatusCode.OK)
        }

        get("/{id}/recommendations") {
            val id = call.getIntParamOrBadRequest("id")
            val recs = bookService.getRecommendations(id)
            call.respond(recs)
        }
    }
}
