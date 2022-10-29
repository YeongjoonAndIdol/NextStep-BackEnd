package com.prject.nextstep.global.security.jwt

import com.prject.nextstep.domain.user.entity.RefreshToken
import com.prject.nextstep.domain.user.entity.repository.RefreshTokenRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtProviderImpl(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
): JwtProvider {

    companion object {
        private const val HEADER = "Authorization"
        private const val PREFIX = "Bearer "
        private const val ACCESS = "access"
        private const val REFRESH = "refresh"
    }

    private val key: Key by lazy {
        val secretKey: String = "ZVc3Z0g4bm5TVzRQUDJxUXBIOGRBUGtjRVg2WDl0dzVYVkMyWWs1Qlk3NkZBOXh1UzNoRWUzeTd6cVdEa0x2eQo=" // base64Encoded
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    override fun generateJwtDto(id: String): JwtDto {
        val accessToken = generateToken(id, ACCESS, jwtProperties.accessExp)
        val refreshToken = generateToken(id, REFRESH, jwtProperties.refreshExp)

        refreshTokenRepository.save(
            RefreshToken(
                id = id,
                token = refreshToken,
                timeToLive = jwtProperties.refreshExp.toLong() / 1000
            )
        )

        return JwtDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiredAt = jwtProperties.accessExp.toLong()
        )
    }

    private fun generateToken(id: String, type: String, exp: Int): String {
        val expiredAt: Date = Date(System.currentTimeMillis() + exp)

        return Jwts.builder()
            .setSubject(id)
            .claim("type", type)
            .setExpiration(expiredAt)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }
}