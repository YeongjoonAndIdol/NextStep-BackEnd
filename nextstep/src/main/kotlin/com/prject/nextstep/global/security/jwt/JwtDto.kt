package com.prject.nextstep.global.security.jwt

data class JwtDto(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: Long
) {
}