package ru.clevertec.config

import di.modules.appModule
import io.ktor.server.application.*
import org.kodein.di.ktor.di
import ru.clevertec.di.modules.kafkaModule
import ru.clevertec.di.modules.repositoryModule
import ru.clevertec.di.modules.serviceModule

fun Application.configureDependencies() {
    di {
        import(appModule)
        import(kafkaModule)
        import(repositoryModule)
        import(serviceModule)
    }
}
