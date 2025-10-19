package ru.clevertec.di.modules

import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val metricsModule = DI.Module("metricsModule") {
    bind<PrometheusMeterRegistry>() with singleton {
        PrometheusMeterRegistry(PrometheusConfig.DEFAULT).apply {
        }
    }
}