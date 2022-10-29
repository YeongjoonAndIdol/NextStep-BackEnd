package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.user.entity.User
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.security.jwt.JwtDto
import com.prject.nextstep.global.security.jwt.JwtProvider
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OauthLoginService(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(oAuth2User: OAuth2User) : JwtDto {
        if(!userRepository.existsByEmail(oAuth2User.attributes["email"] as String)) {
            val user = User.oAuthUser(
                email = oAuth2User.attributes["email"] as String,
                name = oAuth2User.attributes["name"] as String
            )
            userRepository.save(user)

        }

        return jwtProvider.generateJwtDto(oAuth2User.attributes["email"] as String)
    }
}