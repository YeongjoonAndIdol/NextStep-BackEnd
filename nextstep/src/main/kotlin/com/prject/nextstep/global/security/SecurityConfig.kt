package com.prject.nextstep.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.prject.nextstep.global.config.FilterConfig
import com.prject.nextstep.global.security.jwt.JwtParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper
) {


    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf().disable()
            .cors().and()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()

            .antMatchers(HttpMethod.GET, "/oauth2/authorization/google").permitAll()
            .antMatchers(HttpMethod.POST, "/users/signin").permitAll()
            .antMatchers(HttpMethod.POST, "/users/signup").permitAll()
            .anyRequest().permitAll()

        http
            .apply(FilterConfig(jwtParser, objectMapper))

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}