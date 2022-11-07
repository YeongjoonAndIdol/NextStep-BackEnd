package com.prject.nextstep.global.security.principle

import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.exception.InvalidTokenException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        val user = userRepository.findByIdOrNull(UUID.fromString(username)) ?: throw InvalidTokenException

        return CustomUserDetails(user.id)
    }
}