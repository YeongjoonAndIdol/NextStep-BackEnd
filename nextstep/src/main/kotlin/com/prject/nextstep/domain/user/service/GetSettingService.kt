package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.quest.entity.repository.LikedQuestRepository
import com.prject.nextstep.domain.user.dto.response.GetSettingResponse
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetSettingService(
    private val securityUtils: SecurityUtils,
    private val userRepository: UserRepository,
    private val likedQuestRepository: LikedQuestRepository
) {

    @Transactional(readOnly = true)
    fun execute(): GetSettingResponse {

        val userId = securityUtils.getCurrentUserId()
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException
        val likedQuest = likedQuestRepository.findByIdUserId(userId)

        val likedQuestElement = likedQuest.map {
            GetSettingResponse.LikedQuestElement(
                id = it.id.questId,
                name = it.name
            )
        }

        return GetSettingResponse(
            name = user.name,
            likedQuest = likedQuestElement
        )
    }
}