package edu.dongguk.openBadge.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus

data class ErrorResponse(
    @JsonIgnore
    val status: HttpStatus,
    val message: String?,
    val code: Int
) {
    companion object {
        fun generalException(localizedMessage: String?) =
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                message = localizedMessage,
                code = 100
            )

        fun InternalServerException(localizedMessage: String?, code: Int) =
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                message = localizedMessage,
                code = code
            )
    }
}
