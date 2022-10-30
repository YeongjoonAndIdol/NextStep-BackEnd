package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.entity.MyQuest
import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CertificationSuccessQuestService(
    private val securityUtils: SecurityUtils,
    private val myQuestRepository: MyQuestRepository
) {

    @Transactional
    fun execute(questId: UUID) {

        val userId = securityUtils.getCurrentUserId()

        val myQuest = myQuestRepository.findByIdUserIdAndIdQuestId(userId, questId) ?: throw QuestNotFoundException

        myQuestRepository.save(
            MyQuest.complete(
                id = myQuest.id,
                user = myQuest.user,
                quest = myQuest.quest,
                name = myQuest.name
            )
        )
    }
}