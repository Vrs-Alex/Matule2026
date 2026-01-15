package vrsalex.matule.exception.handler

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import vrsalex.matule.exception.ErrorResponse

/*
ONLY LOCAL TESTING
*/


@RestControllerAdvice
@Order(4)
class GlobalGenericExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        ex.printStackTrace()
        return ResponseEntity(
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "Внутренняя ошибка сервера: ${ex.message}"
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}