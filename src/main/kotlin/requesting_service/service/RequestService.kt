package requesting_service.service

import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.dto.ScheduledMessageDto

interface RequestService {

    fun sendMessage(messageDto: MessageDto): Mono<Void>

    fun sendScheduledMessage(scheduledMessageDto: ScheduledMessageDto): Mono<Void>
}