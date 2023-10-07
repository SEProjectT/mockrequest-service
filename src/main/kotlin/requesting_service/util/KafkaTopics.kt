package requesting_service.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KafkaTopics {

    @Value("\${topic.immediate}")
    lateinit var topicImmediate: String

    @Value("\${topic.scheduled}")
    lateinit var topicScheduled: String
}