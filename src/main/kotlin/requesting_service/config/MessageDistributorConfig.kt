package requesting_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import requesting_service.dto.MessageType
import requesting_service.service.MessageDistributor
import requesting_service.service.impl.distributor.ImmediateMessageDistributor
import requesting_service.service.impl.distributor.ScheduledMessageDistributor

@Configuration
class MessageDistributorConfig(
    private val immediate: ImmediateMessageDistributor,
    private val scheduled: ScheduledMessageDistributor
) {

    @Bean
    fun messageDistributors(): Map<MessageType, MessageDistributor> {
        return mapOf(
            MessageType.IMMEDIATE to immediate,
            MessageType.SCHEDULED to scheduled
        )
    }
}