package com.prject.nextstep.global.security.jwt

import com.nimbusds.oauth2.sdk.token.AccessTokenType
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//
//        val jwt = jwtProvider.resolveToken(request)
//
//        if(StringUtils.hasText(jwt) && jwtProvider.validateAccessToken(jwt)) {
//            val authentication = jwtProvider.findAuthentication(jwt)
//            System.out.println("adsfafad")
//            SecurityContextHolder.getContext().authentication = authentication
//            System.out.println("adddsdfdsfsddsfafad")
//        }
//        System.out.println("324r32423")
//        filterChain.doFilter(request, response)
//    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)
        SecurityContextHolder.getContext().authentication = jwtProvider.findAuthentication(token)

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String {
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        bearerToken?.let {
            if (bearerToken.startsWith(AccessTokenType.BEARER.value)) {
                return bearerToken.substring(7)
            }
        }

        return ""
    }

}