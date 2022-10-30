package com.prject.nextstep.domain.user.dto.response

data class GetPerformanceResponse(
    val weekAchievement: List<Boolean>,
    val completedQuests: List<CompletedQuestElement>
) {

    data class CompletedQuestElement(
        val type: String,
        val title: String,
        val content: String,
        val exp: Int
    )
}