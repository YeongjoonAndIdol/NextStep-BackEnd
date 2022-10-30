package com.prject.nextstep.domain.user.dto

import java.util.UUID

data class RankingResponseDto(
    val id: UUID,
    val name: String,
    val ranking: Int,
    val level: Int
)