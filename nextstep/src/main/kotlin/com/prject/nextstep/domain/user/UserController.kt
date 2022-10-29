package com.prject.nextstep.domain.user

import com.prject.nextstep.domain.user.dto.request.UserSignInRequest
import com.prject.nextstep.domain.user.dto.request.UserSignUpRequest
import com.prject.nextstep.domain.user.service.OauthLoginService
import com.prject.nextstep.domain.user.service.UserSignInService
import com.prject.nextstep.domain.user.service.UserSignUpService
import com.prject.nextstep.global.security.jwt.JwtDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val oauthLoginService: OauthLoginService,
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService
) {

    @GetMapping("/oauth/login")
    fun oauthLogin(@AuthenticationPrincipal oAuth2User: OAuth2User): ResponseEntity<JwtDto> {
        return ResponseEntity.ok(oauthLoginService.execute(oAuth2User))
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun singUp(@RequestBody @Valid request: UserSignUpRequest): JwtDto {
        return userSignUpService.execute(request)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: UserSignInRequest): JwtDto {
        return userSignInService.execute(request)
    }
}