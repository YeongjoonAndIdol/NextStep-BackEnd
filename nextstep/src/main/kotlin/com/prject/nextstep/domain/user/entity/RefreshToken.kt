package com.prject.nextstep.domain.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import javax.validation.constraints.NotNull

@RedisHash("tbl_refresh_token")
data class RefreshToken(
    @field:Id
    val id: String,

    @field:NotNull
    val token: String,

    @field:TimeToLive
    val timeToLive: Long
)