package com.prject.nextstep.domain.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserSignInRequest(

    @field:NotBlank
    val accountId: String,

    @field:NotBlank
    @field:Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*[!@#\$%^&*+=-])(?=.*[0-9]).{8,20}\$/")
    val password: String
)
