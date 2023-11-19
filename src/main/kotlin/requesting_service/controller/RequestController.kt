package requesting_service.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.service.RequestService
import java.util.logging.Logger

@RestController
@RequestMapping("/request")
class RequestController(
    private val requestService: RequestService
) {

    private var logger: Logger = Logger.getLogger(this::class.java.name)

    @PostMapping("/")
    fun sendMessage(@Valid @RequestBody messageDto: MessageDto): Mono<Void> {
        logger.info("Request to send message: $messageDto")

        return requestService.sendMessage(messageDto)
    }
}