package com.prject.nextstep.domain.user.dto.response

import java.util.UUID

data class MyPageResponse(
    val name: String,
    val level: Int,
    val exp: Int,
    val walkCount: Int,
    val ranking: Int,
    val myRoutine: List<MyQuestElement>
) {
    data class MyQuestElement(
        val id: UUID,
        val name: String
    )
}