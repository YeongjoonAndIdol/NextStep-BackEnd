package com.prject.nextstep.domain.retrospects.service

import com.prject.nextstep.domain.retrospects.entity.Retrospects
import com.prject.nextstep.domain.retrospects.entity.repository.RetrospectsRepository
import com.prject.nextstep.domain.retrospects.exception.RetrospectsAlreadyExistsException
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class CreateRetrospectsService(
    private val securityUtils: SecurityUtils,
    private val userRepository: UserRepository,
    private val retrospectsRepository: RetrospectsRepository
) {

    @Transactional
    fun execute(content: String) {

        val userId = securityUtils.getCurrentUserId()
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException

        if(retrospectsRepository.findByUserIdAndDate(userId, LocalDate.now()) != null) {
            throw RetrospectsAlreadyExistsException
        }

        retrospectsRepository.save(
            Retrospects(
                content = content,
                date = LocalDate.now(),
                user = user
            )
        )
    }
}