package di.modules

import io.ktor.server.application.ApplicationEnvironment
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val appModule = DI.Module("appModule") { environment: ApplicationEnvironment ->
    bind<ApplicationEnvironment>() with singleton { environment }
}