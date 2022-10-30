package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.entity.LikedQuest
import com.prject.nextstep.domain.quest.entity.LikedQuestId
import com.prject.nextstep.domain.quest.entity.repository.LikedQuestRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateRecommendedService(
    private val securityUtils: SecurityUtils,
    private val likedQuestRepository: LikedQuestRepository,
    private val questRepository: QuestRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(questId: UUID) {

        val userId = securityUtils.getCurrentUserId()

        val myLiked = likedQuestRepository.findByIdUserIdAndIdQuestId(userId, questId)

        if (myLiked != null) likedQuestRepository.delete(myLiked)
        else {
            val quest = questRepository.findByIdOrNull(questId) ?: throw QuestNotFoundException
            val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException

            likedQuestRepository.save(LikedQuest(LikedQuestId(userId, questId), name = quest.name, user = user, quest = quest))
        }
    }
}