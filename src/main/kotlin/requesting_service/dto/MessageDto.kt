package requesting_service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class MessageDto(

    @NotNull(message = "List of receiver ids can't be null")
    val receiverIds: List<Long>,

    @NotBlank(message = "Message can't be blank")
    val message: String,

    val scheduledAt: LocalDateTime?
)