package ru.clevertec.di.modules

import KafkaConfig
import getKafkaConfig
import io.ktor.server.application.*
import ru.clevertec.kafka.producer.KafkaProducerServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.clevertec.kafka.producer.KafkaProducerService

val kafkaModule = DI.Module("kafkaModule") {
    bind<KafkaConfig>() with singleton { instance<ApplicationEnvironment>().config.getKafkaConfig() }
    bind<KafkaProducerService>() with singleton { KafkaProducerServiceImpl(instance()) }
}