package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.response.GetRecommendQuestsResponse
import com.prject.nextstep.domain.quest.entity.repository.LikedQuestRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetRecommendQuestsService(
    private val securityUtils: SecurityUtils,
    private val questRepository: QuestRepository,
    private val likedQuestRepository: LikedQuestRepository
) {

    @Transactional(readOnly = true)
    fun execute(): GetRecommendQuestsResponse {

        val userId = securityUtils.getCurrentUserId()

        val recommendQuests = questRepository.getRecommend().map {
            val isLiked = likedQuestRepository.findByIdUserIdAndIdQuestId(userId, it.id) != null
            GetRecommendQuestsResponse.RecommendQuestElement(
                id = it.id,
                name = it.name,
                isLiked = isLiked,
                likeCount = likedQuestRepository.countByIdQuestId(it.id)
            )
        }

        return GetRecommendQuestsResponse(
            recommendQuests = recommendQuests
        )
    }
}