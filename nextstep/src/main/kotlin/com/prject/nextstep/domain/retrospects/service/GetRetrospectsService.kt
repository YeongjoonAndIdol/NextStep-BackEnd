package com.prject.nextstep.domain.retrospects.service

import com.prject.nextstep.domain.retrospects.dto.response.GetRetrospectsResponse
import com.prject.nextstep.domain.retrospects.entity.repository.RetrospectsRepository
import com.prject.nextstep.domain.retrospects.exception.RetrospectsNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GetRetrospectsService(
    private val securityUtils: SecurityUtils,
    private val retrospectsRepository: RetrospectsRepository
) {

    fun execute(date: LocalDate): GetRetrospectsResponse {

        val userId = securityUtils.getCurrentUserId()
        val retrospects = retrospectsRepository.findByUserIdAndDate(userId, date)
            ?: throw RetrospectsNotFoundException

        return GetRetrospectsResponse(
            content = retrospects.content
        )
    }
}