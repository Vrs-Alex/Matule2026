package vrsalex.matule.exception.handler

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import vrsalex.matule.exception.ErrorResponse

@RestControllerAdvice
@Order(1)
class GlobalSerializationExceptionHandler {

    @OptIn(ExperimentalSerializationApi::class)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleJsonReadingException(ex: HttpMessageNotReadableException): ErrorResponse {
        val rootCause = ex.cause

        if (rootCause is MissingFieldException) {
            val missingFields = rootCause.missingFields.joinToString(", ")
            return ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = "Некорректный запрос: отсутствуют обязательные поля: $missingFields."
            )
        }
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Невозможно прочитать тело запроса. Проверьте формат JSON."
        )
    }
}
