package com.prject.nextstep.global.security

import com.prject.nextstep.global.security.jwt.JwtFilter
import com.prject.nextstep.global.security.jwt.JwtProvider
import com.prject.nextstep.global.security.oauth.CustomOAuthDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val customUserDetailService: CustomOAuthDetailService,
    private val jwtProvider: JwtProvider
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
//            .oauth2Login {
//                it.userInfoEndpoint().userService(customUserDetailService) // #2
//                it.defaultSuccessUrl("/users/oauth/login") // #3
//                it.failureUrl("/fail")
//            }
            .addFilterBefore(JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)

        http
            .authorizeRequests()

            .antMatchers(HttpMethod.GET, "/oauth2/authorization/google").permitAll()
            .antMatchers(HttpMethod.POST, "/users/signin").permitAll()
            .antMatchers(HttpMethod.POST, "/users/signup").permitAll()
            .anyRequest().permitAll()
        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}