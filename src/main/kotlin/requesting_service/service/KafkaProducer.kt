package requesting_service.service

import reactor.core.publisher.Mono

interface KafkaProducer {

    fun <T> produce(topic: String, t: T): Mono<Void>
}