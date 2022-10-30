package com.prject.nextstep.domain.quest.dto.response

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class GetQuestListResponse(
    @field:NotNull
    val quests: List<QuestElement>,

    @field:NotNull
    val progress: Long
) {
    data class QuestElement(
        @field:NotBlank
        val id: UUID,

        @field:NotBlank
        val name: String,

        @field:NotNull
        val isComplete: Boolean
    )
}