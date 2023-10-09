package requesting_service.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.service.RequestService

@RestController
@RequestMapping("/request")
class RequestController(@Autowired val requestService: RequestService) {

    @PostMapping("/")
    fun sendMessage(@Valid @RequestBody messageDto: MessageDto): Mono<Void> {
        return requestService.sendMessage(messageDto)
    }
}