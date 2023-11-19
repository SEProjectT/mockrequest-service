package requesting_service.service.impl.distributor

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.service.KafkaProducer
import requesting_service.service.MessageDistributor

@Component
class ImmediateMessageDistributor(
    private val kafkaProducer: KafkaProducer
): MessageDistributor {

    @Value("\${topic.immediate}")
    private val immediateTopic = ""

    override fun distribute(messageDto: MessageDto): Mono<Void> =
        kafkaProducer.produce(immediateTopic, messageDto)
}