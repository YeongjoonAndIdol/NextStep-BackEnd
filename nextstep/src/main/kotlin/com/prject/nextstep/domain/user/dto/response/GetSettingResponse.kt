package com.prject.nextstep.domain.user.dto.response

import java.util.UUID

data class GetSettingResponse(
    val name: String,
    val likedQuest: List<LikedQuestElement>
) {
    data class LikedQuestElement(
        val id: UUID,
        val name: String
    )
}