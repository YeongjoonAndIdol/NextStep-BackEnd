package com.prject.nextstep.domain.quest.dto.response

import java.util.UUID

data class SearchQuestResponse(
    val quests: List<QuestElement>
) {
    data class QuestElement(
        val id: UUID,
        val name: String,
        val isLiked: Boolean,
        val likeCount: Long
    )
}