package com.prject.nextstep.domain.user.entity

import com.prject.nextstep.global.security.jwt.Authority
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import javax.validation.constraints.NotNull

@RedisHash("tbl_refresh_token")
data class RefreshToken(

    @Id
    val token: String,

    @field:NotNull
    val userId: UUID,

    @field:NotNull
    val authority: Authority,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int

)