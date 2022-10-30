package com.prject.nextstep.domain.quest.dto.request

import javax.validation.constraints.NotBlank

data class CreateQuestRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val period: String,

    @field:NotBlank
    val time: String,

    @field:NotBlank
    val content: String,

    @field:NotBlank
    val schoolType: String
)