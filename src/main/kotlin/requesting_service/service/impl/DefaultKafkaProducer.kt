package requesting_service.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import requesting_service.service.KafkaProducer
import java.util.logging.Logger

@Service
class DefaultKafkaProducer(@Autowired val kafkaTemplate: KafkaTemplate<String, Any>): KafkaProducer {

    private var logger: Logger = Logger.getLogger(DefaultKafkaProducer::class.java.name)

    override fun <T> produce(topic: String, t: T): Mono<Void> {
        return Mono.fromFuture { kafkaTemplate.send(topic, t)
            .whenComplete { res, th ->
                logger.info("produced message: ${res.producerRecord} + $topic + ${res.producerRecord.topic()}")
            } }
            .then()
    }
}