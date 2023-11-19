package requesting_service.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import reactor.core.publisher.Mono
import requesting_service.dto.MessageDto
import requesting_service.dto.MessageType
import requesting_service.service.impl.RequestServiceImpl
import requesting_service.service.impl.distributor.ImmediateMessageDistributor
import requesting_service.service.impl.distributor.ScheduledMessageDistributor
import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.TemporalUnit

@ExtendWith(MockitoExtension::class)
class RequestServiceTest {

    @Mock
    private lateinit var immediateMessageDistributor: ImmediateMessageDistributor

    @Mock
    private lateinit var scheduledMessageDistributor: ScheduledMessageDistributor

    @Mock
    private lateinit var messageDistributors: Map<MessageType, MessageDistributor>

    private lateinit var requestService: RequestServiceImpl

    @BeforeEach
    fun setUp() {
        requestService = RequestServiceImpl(messageDistributors)

        Mockito
            .lenient()
            .`when`(immediateMessageDistributor.distribute(mockImmediateMessageDto()))
            .thenReturn(Mono.empty())

        Mockito
            .lenient()
            .`when`(scheduledMessageDistributor.distribute(mockScheduledMessageDto()))
            .thenReturn(Mono.empty())
    }

    @Test
    fun sendMessage_shouldSendToImmediateDistributor() {
        Mockito
            .`when`(messageDistributors[MessageType.IMMEDIATE])
            .thenReturn(immediateMessageDistributor)

        requestService.sendMessage(mockImmediateMessageDto())
            .subscribe()

        Mockito
            .verify(immediateMessageDistributor)
            .distribute(mockImmediateMessageDto())
    }

    @Test
    fun sendMessage_shouldSendToScheduledDistributor() {
        Mockito
            .`when`(messageDistributors[MessageType.SCHEDULED])
            .thenReturn(scheduledMessageDistributor)

        requestService.sendMessage(mockScheduledMessageDto())

        Mockito
            .verify(scheduledMessageDistributor)
            .distribute(mockScheduledMessageDto())
    }

    private fun mockImmediateMessageDto(): MessageDto =
        MessageDto(listOf(1, 2), "Hello", null)

    private fun mockScheduledMessageDto(): MessageDto =
        MessageDto(listOf(1, 2), "Hello", LocalDateTime.of(2022, Month.APRIL, 1, 2, 3))
}