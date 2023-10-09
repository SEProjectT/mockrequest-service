package requesting_service.service.impl.distributor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.service.KafkaProducer
import requesting_service.service.MessageDistributor

@Component
class ScheduledMessageDistributor(@Autowired val kafkaProducer: KafkaProducer): MessageDistributor {

    @Value("\${topic.scheduled}")
    private val scheduledTopic= ""

    @Transactional(propagation = Propagation.NESTED)
    override fun distribute(messageDto: MessageDto): Mono<Void> {
        return kafkaProducer.produce(scheduledTopic, messageDto)
    }
}