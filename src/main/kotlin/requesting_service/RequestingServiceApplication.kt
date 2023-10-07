package requesting_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RequestingServiceApplication

fun main(args: Array<String>) {
    runApplication<RequestingServiceApplication>(*args)
}