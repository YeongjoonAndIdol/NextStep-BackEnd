package com.prject.nextstep.domain.user.dto.request

import javax.validation.constraints.NotBlank

data class UserSignUpRequest(

    @field:NotBlank
    val accountId: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val name: String
)
