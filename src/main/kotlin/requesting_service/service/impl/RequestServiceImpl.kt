package requesting_service.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.dto.ScheduledMessageDto
import requesting_service.service.KafkaProducer
import requesting_service.service.RequestService
import requesting_service.util.KafkaTopics

@Service
@Transactional(readOnly = true)
class RequestServiceImpl(@Autowired val kafkaProducer: KafkaProducer, val kafkaTopics: KafkaTopics): RequestService {

    @Transactional
    override fun sendMessage(messageDto: MessageDto): Mono<Void> {
        return kafkaProducer.produce(kafkaTopics.topicImmediate, messageDto)
    }

    @Transactional
    override fun sendScheduledMessage(scheduledMessageDto: ScheduledMessageDto): Mono<Void> {
        return kafkaProducer.produce(kafkaTopics.topicScheduled, scheduledMessageDto)
    }
}