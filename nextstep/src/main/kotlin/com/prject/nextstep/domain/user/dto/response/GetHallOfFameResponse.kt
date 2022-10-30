package com.prject.nextstep.domain.user.dto.response

import java.util.UUID

data class GetHallOfFameResponse(
    val userRanking: List<UserRankingElement>,
    val myRanking: MyRankingElement
) {
    data class UserRankingElement(
        val id: UUID,
        val name: String,
        val ranking: Int,
        val level: Int
    )

    data class MyRankingElement(
        val name: String,
        val ranking: Int,
        val level: Int
    )
}