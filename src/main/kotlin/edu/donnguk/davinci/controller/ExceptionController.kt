package edu.donnguk.davinci.controller

import edu.donnguk.davinci.dto.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionController {
    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("Occurred a general exception", e)
        val response = ErrorResponse.generalException(e.localizedMessage)
        return ResponseEntity.status(response.status).body(response)
    }
}