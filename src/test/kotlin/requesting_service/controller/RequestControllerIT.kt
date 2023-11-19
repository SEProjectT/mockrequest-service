package requesting_service.controller

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import requesting_service.dto.MessageDto

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RequestControllerIT {

    companion object {
        @Container
        var kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            kafka.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers)
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            kafka.stop()
        }
    }

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun sendMessage() {
        webTestClient.post().uri("/request/")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(mockMessage())
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody().isEmpty
    }

    private fun mockMessage(): MessageDto {
        return MessageDto(listOf(1L, 2L), "message", null)
    }
}