package requesting_service.service

import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto

interface RequestService {

    fun sendMessage(messageDto: MessageDto): Mono<Void>
}