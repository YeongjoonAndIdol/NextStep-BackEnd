package com.prject.nextstep.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    private val customUserDetailService: CustomUserDetailService
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .httpBasic {
                it.disable()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.NEVER) // #1
            }
            .oauth2Login {
                it.userInfoEndpoint().userService(customUserDetailService) // #2
                it.defaultSuccessUrl("/users/oauth/login") // #3
                it.failureUrl("/fail")
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}