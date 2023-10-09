package requesting_service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class MessageDto(

    @NotNull
    val receiverIds: List<Long>,

    @NotBlank
    val message: String,

    val scheduledAt: LocalDateTime?
)