package requesting_service.service

import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto

interface MessageDistributor {

    fun distribute(messageDto: MessageDto): Mono<Void>
}