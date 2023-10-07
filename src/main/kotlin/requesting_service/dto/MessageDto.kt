package requesting_service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class MessageDto(

    @NotNull
    val receiverIds: List<Long>,

    @NotBlank
    val message: String
)