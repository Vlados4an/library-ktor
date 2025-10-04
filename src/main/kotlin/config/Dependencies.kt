package ru.clevertec.config

import io.ktor.server.application.*
import org.kodein.di.ktor.di
import ru.clevertec.di.modules.repositoryModule
import ru.clevertec.di.modules.serviceModule

fun Application.configureDependencies() {
    di {
        import(repositoryModule)
        import(serviceModule)
    }
}
