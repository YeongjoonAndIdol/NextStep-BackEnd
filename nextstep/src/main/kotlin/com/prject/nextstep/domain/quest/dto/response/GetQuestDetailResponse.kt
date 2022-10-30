package com.prject.nextstep.domain.quest.dto.response

data class GetQuestDetailResponse(
    val name: String,
    val lists: List<ListElement>
) {
    data class ListElement(
        val type: String,
        val title: String,
        val content: String,
        val exp: Int
    )
}
