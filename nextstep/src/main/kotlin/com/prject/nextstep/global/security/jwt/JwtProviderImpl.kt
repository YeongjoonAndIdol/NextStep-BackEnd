package com.prject.nextstep.global.security.jwt

import com.nimbusds.oauth2.sdk.token.AccessTokenType
import com.prject.nextstep.domain.user.entity.RefreshToken
import com.prject.nextstep.domain.user.entity.repository.RefreshTokenRepository
import com.prject.nextstep.global.security.principle.CustomUserDetailService
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtProviderImpl(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userDetailsService: CustomUserDetailService
): JwtProvider {

    companion object {
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

    override fun resolveToken(request: HttpServletRequest): String {
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        bearerToken?.let {
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AccessTokenType.BEARER.value)) {
                return bearerToken.substring(7)
            }
        }

        return ""
    }

    override fun validateAccessToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            println("올바르지 못한 토큰입니다.")
        } catch (e: MalformedJwtException) {
            println("올바르지 못한 토큰입니다.")
        } catch (e: ExpiredJwtException) {
            println("만료된 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            println("지원되지 않는 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            println("잘못된 토큰입니다.")
        }
        return true
    }

    override fun findAuthentication(accessToken: String): Authentication {
        val id = parseClaims(accessToken).subject

        val userDetails = userDetailsService.loadUserByUsername(id)

        return UsernamePasswordAuthenticationToken(userDetails, "")
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}