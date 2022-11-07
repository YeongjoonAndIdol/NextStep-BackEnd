package com.prject.nextstep.domain.user

import com.prject.nextstep.domain.user.dto.request.UserSignInRequest
import com.prject.nextstep.domain.user.dto.request.UserSignUpRequest
import com.prject.nextstep.domain.user.dto.response.GetHallOfFameResponse
import com.prject.nextstep.domain.user.dto.response.GetPerformanceResponse
import com.prject.nextstep.domain.user.dto.response.GetSettingResponse
import com.prject.nextstep.domain.user.dto.response.MyPageResponse
import com.prject.nextstep.domain.user.service.*
import com.prject.nextstep.global.security.jwt.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/users")
class UserController(
//    private val oauthLoginService: OauthLoginService,
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val getMyPageService: GetMyPageService,
    private val getSettingService: GetSettingService,
    private val getHallOfFameService: GetHallOfFameService,
    private val getPerformanceService: GetPerformanceService
) {

//    @GetMapping("/oauth/login")
//    fun oauthLogin(@AuthenticationPrincipal oAuth2User: OAuth2User): ResponseEntity<TokenResponse> {
//        return ResponseEntity.ok(oauthLoginService.execute(oAuth2User))
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun singUp(@RequestBody @Valid request: UserSignUpRequest): TokenResponse {
        return userSignUpService.execute(request)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: UserSignInRequest): TokenResponse {
        return userSignInService.execute(request)
    }

    @GetMapping
    fun myPage(): MyPageResponse {
        return getMyPageService.execute()
    }

    @GetMapping("/setting")
    fun getSetting(): GetSettingResponse {
        return getSettingService.execute()
    }

    @GetMapping("/performance")
    fun getPerformance(): GetPerformanceResponse {
        return getPerformanceService.execute()
    }

    @GetMapping("/hall-of-fame")
    fun getHallOfFame(): GetHallOfFameResponse {
        return getHallOfFameService.execute()
    }
}