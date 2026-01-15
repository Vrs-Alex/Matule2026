package vrsalex.matule.exception.handler

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import vrsalex.matule.exception.ErrorResponse
import vrsalex.matule.security.exception.MySecurityException

@RestControllerAdvice
@Order(2)
class GlobalSecurityExceptionHandler {


    @ExceptionHandler(MySecurityException::class)
    fun handleSecurityException(ex: MySecurityException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = ex.errorCode,
            message = ex.message ?: "Invalid input data."
        )
        return ResponseEntity(errorResponse, HttpStatus.valueOf(ex.errorCode))
    }

}

