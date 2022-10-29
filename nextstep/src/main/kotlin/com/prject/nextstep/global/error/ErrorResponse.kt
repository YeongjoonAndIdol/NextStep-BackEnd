package com.prject.nextstep.global.error

import org.springframework.validation.FieldError
import org.springframework.validation.BindException

data class ErrorResponse(
    val status: Int,
    val message: String
) {

    companion object {
        fun of(errorProperty: ErrorProperty) = ErrorResponse(
            errorProperty.status(),
            errorProperty.message()
        )

        fun of(e: BindException): BindErrorResponse {
            val errorMap = HashMap<String, String?>()

            for (error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return BindErrorResponse(
                status = ErrorCode.BAD_REQUEST.status(),
                fieldError = listOf(errorMap)
            )
        }
    }
}

data class BindErrorResponse(
    val status: Int,
    val fieldError: List<Map<String, String?>>
)