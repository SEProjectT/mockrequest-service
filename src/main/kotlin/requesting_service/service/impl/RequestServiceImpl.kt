package requesting_service.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.dto.MessageType
import requesting_service.service.MessageDistributor
import requesting_service.service.RequestService

@Service
class RequestServiceImpl(@Autowired val messageDistributors: Map<MessageType, MessageDistributor>): RequestService {

    @Transactional
    override fun sendMessage(messageDto: MessageDto): Mono<Void> {
        val messageType = if (messageDto.scheduledAt != null) {
            MessageType.SCHEDULED
        } else {
            MessageType.IMMEDIATE
        }

        return messageDistributors[messageType]!!.distribute(messageDto)
    }
}