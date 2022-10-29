package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.user.dto.request.UserSignInRequest
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.security.jwt.JwtDto
import org.springframework.stereotype.Service

@Service
class UserSignInService(
    private val userRepository: UserRepository
) {

    fun execute(userSignInRequest: UserSignInRequest): JwtDto {
        userRepository.findByAccountId(userSignInRequest.accountId)
    }
}