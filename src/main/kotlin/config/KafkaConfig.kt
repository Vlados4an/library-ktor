import io.ktor.server.config.*

data class KafkaConfig(
    val bootstrapServers: String,
    val producer: ProducerConfig,
    val topics: Topics
) {
    data class ProducerConfig(
        val clientId: String,
        val acks: String,
        val retries: Int
    )

    data class Topics(
        val bookEvents: String,
        val authorEvents: String
    )
}

fun ApplicationConfig.getKafkaConfig(): KafkaConfig {
    return KafkaConfig(
        bootstrapServers = property("kafka.bootstrapServers").getString(),
        producer = KafkaConfig.ProducerConfig(
            clientId = property("kafka.producer.clientId").getString(),
            acks = property("kafka.producer.acks").getString(),
            retries = property("kafka.producer.retries").getString().toInt(),
        ),
        topics = KafkaConfig.Topics(
            bookEvents = property("kafka.topics.bookEvents").getString(),
            authorEvents = property("kafka.topics.authorEvents").getString()
        )
    )
}