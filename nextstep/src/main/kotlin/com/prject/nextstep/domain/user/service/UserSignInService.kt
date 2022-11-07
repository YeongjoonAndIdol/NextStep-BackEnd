package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.user.dto.request.UserSignInRequest
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.domain.user.exception.UserPasswordNotMatchedException
import com.prject.nextstep.global.security.jwt.Authority
import com.prject.nextstep.global.security.jwt.JwtProvider
import com.prject.nextstep.global.security.jwt.TokenResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignInService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @Transactional
    fun execute(userSignInRequest: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(userSignInRequest.accountId) ?: throw UserNotFoundException

        if (!bCryptPasswordEncoder.matches(userSignInRequest.password, user.password)) {
            throw UserPasswordNotMatchedException
        }
        return jwtProvider.receiveToken(user.id, Authority.USER)
    }
}