package com.prject.nextstep.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.prject.nextstep.global.error.GlobalExceptionFilter
import com.prject.nextstep.global.security.jwt.JwtFilter
import com.prject.nextstep.global.security.jwt.JwtParser
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

/**
 *
 * FilterConfig
 *
 * @author ljcha
 * @date 2022-11-07
 * @version 1.0.0
 **/
@Component
class FilterConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper
) :  SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}