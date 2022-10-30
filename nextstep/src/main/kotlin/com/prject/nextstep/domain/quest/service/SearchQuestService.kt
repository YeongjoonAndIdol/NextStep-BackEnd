package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.response.SearchQuestResponse
import com.prject.nextstep.domain.quest.entity.repository.LikedQuestRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchQuestService(
    private val securityUtils: SecurityUtils,
    private val questRepository: QuestRepository,
    private val likedQuestRepository: LikedQuestRepository
) {

    @Transactional(readOnly = true)
    fun execute(name: String): SearchQuestResponse {

        val userId = securityUtils.getCurrentUserId()
        val quests= questRepository.findByNameContaining(name).map {
            val isLiked = likedQuestRepository.findByIdUserIdAndIdQuestId(userId, it.id) != null
            SearchQuestResponse.QuestElement(
                id = it.id,
                name = it.name,
                isLiked = isLiked,
                likeCount = likedQuestRepository.countByIdQuestId(it.id)
            )
        }

        return SearchQuestResponse(
            quests = quests
        )
    }
}