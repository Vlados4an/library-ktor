package ru.clevertec.config

import io.ktor.server.application.*
import io.ktor.server.routing.*
import routes.bookRoutes
import routes.genreRoutes
import routes.metrics
import ru.clevertec.routes.authorRoutes

fun Application.configureRouting() {
    routing {
        bookRoutes()
        authorRoutes()
        genreRoutes()
        metrics()
    }
}
