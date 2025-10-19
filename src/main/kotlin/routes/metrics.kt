package routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.metrics() {
    val registry by closestDI().instance<PrometheusMeterRegistry>()

    get("/metrics") {
        call.respondText {
            registry.scrape()
        }
    }
}