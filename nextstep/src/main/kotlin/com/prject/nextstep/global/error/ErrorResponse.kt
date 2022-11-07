package com.prject.nextstep.global.error

import org.springframework.validation.FieldError
import org.springframework.validation.BindException

data class ErrorResponse(
    val status: Int,
    val message: String
)