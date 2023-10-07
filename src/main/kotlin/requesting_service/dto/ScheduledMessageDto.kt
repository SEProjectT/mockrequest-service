package requesting_service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class ScheduledMessageDto(

    @NotNull
    val receiverIds: List<Long>,

    @NotBlank
    val message: String,

    @NotBlank
    val scheduledAt: LocalDateTime
)