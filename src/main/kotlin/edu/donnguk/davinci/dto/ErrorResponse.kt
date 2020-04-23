package edu.donnguk.davinci.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus

data class ErrorResponse (
    @JsonIgnore
    val status: HttpStatus,
    val message: String
) {
    companion object {
        fun generalException(message: String) =
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                message = message
            )
    }
}