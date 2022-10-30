package com.prject.nextstep.domain.retrospects.dto.request

import javax.validation.constraints.NotBlank

data class UpdateRetrospectsRequest (

    @field:NotBlank
    val content: String
)