package com.prject.nextstep.domain.quest.dto.response

import java.util.UUID

data class GetRecommendQuestsResponse(
    val recommendQuests: List<RecommendQuestElement>
) {
    data class RecommendQuestElement(
        val id: UUID,
        val name: String,
        val isLiked: Boolean,
        val likeCount: Long
    )
}
