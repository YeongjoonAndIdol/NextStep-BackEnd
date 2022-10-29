package com.prject.nextstep.global.security.jwt

interface JwtProvider {
    fun generateJwtDto(id: String) : JwtDto
}