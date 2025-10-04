package ru.clevertec.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import ru.clevertec.exception.EntityNotFountException

fun Application.configureExceptions() {
    install(StatusPages) {
        exception<EntityNotFountException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, cause.message ?: "Not found")
        }
    }
}