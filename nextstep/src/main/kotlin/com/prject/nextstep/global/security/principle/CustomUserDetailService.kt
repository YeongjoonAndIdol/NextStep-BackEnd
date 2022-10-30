package com.prject.nextstep.global.security.principle

import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.error.UserCredentialsNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {

        val user = userRepository.findByAccountId(id) ?: userRepository.findByEmail(id)
        ?: throw UserCredentialsNotFoundException

        return CustomUserDetails(user.id)
    }
}