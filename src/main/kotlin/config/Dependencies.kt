package ru.clevertec.config

import di.modules.createAppModule
import io.ktor.server.application.*
import org.kodein.di.ktor.di
import ru.clevertec.di.modules.kafkaModule
import ru.clevertec.di.modules.metricsModule
import ru.clevertec.di.modules.repositoryModule
import ru.clevertec.di.modules.serviceModule

fun Application.configureDependencies() {
    di {
        import(createAppModule(environment))
        import(kafkaModule)
        import(metricsModule)
        import(repositoryModule)
        import(serviceModule)
    }
}
