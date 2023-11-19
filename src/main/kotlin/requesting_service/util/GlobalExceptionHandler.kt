package requesting_service.util

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Any?>? {
        val errors: MutableMap<String, String?> = HashMap()

        ex.bindingResult.fieldErrors
            .forEach { error: FieldError ->
                val fieldName = error.field
                val errorMessage = error.defaultMessage
                errors[fieldName] = errorMessage
                logger.error("Error occurred: $errorMessage")
            }

        return ResponseEntity.badRequest().body(errors)
    }
}