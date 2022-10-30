package com.prject.nextstep.domain.quest.dto.request

data class CreateListRequest(
    val lists: List<ListElement>
) {
    data class ListElement(
        val title: String,
        val content: String,
        val type: String,
        val exp: Int
    )
}