package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.user.dto.request.UserSignUpRequest
import com.prject.nextstep.domain.user.entity.User
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.security.jwt.JwtDto
import com.prject.nextstep.global.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {

    @Transactional
    fun execute(request: UserSignUpRequest): JwtDto {
        val user = User.signUpUser(
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password)
        )
        userRepository.save(user)

        return jwtProvider.generateJwtDto(request.accountId)
    }
}