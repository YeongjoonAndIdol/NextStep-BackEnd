package com.prject.nextstep.global.security.jwt

import java.time.LocalDateTime

/**
 *
 * TokenResponse
 *
 * @author ljcha
 * @date 2022-11-07
 * @version 1.0.0
 **/
data class TokenResponse(
    val accessToken: String,
    val expiredAt: LocalDateTime,
    val refreshToken: String,
    val authority: Authority?
)