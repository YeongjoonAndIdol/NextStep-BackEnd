package com.prject.nextstep.global.security.jwt

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface JwtProvider {
    fun generateJwtDto(id: String) : JwtDto
    fun resolveToken(request: HttpServletRequest): String
    fun validateAccessToken(token: String): Boolean
    fun findAuthentication(accessToken: String): Authentication
}