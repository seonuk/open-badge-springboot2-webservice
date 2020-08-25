package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.dtos.ErrorResponse
import edu.dongguk.openBadge.exception.InternalServerException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("Occurred a general exception", e)
        val response = ErrorResponse.generalException(e.localizedMessage)
        return ResponseEntity.status(response.status).body(response)
    }

    @ExceptionHandler(InternalServerException::class)
    fun handleInternalServerException(e: InternalServerException): ResponseEntity<ErrorResponse> {
        log.error("Internal server error", e)
        val response = ErrorResponse.InternalServerException(e.localizedMessage, e.code)
        return ResponseEntity.status(response.status).body(response)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
}
