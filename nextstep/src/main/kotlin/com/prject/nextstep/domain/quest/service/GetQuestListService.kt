package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.Type
import com.prject.nextstep.domain.quest.dto.response.GetQuestListResponse
import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class GetQuestListService(
    private val myQuestRepository: MyQuestRepository,
    private val securityUtils: SecurityUtils
) {

    @Transactional(readOnly = true)
    fun execute(type: Type): GetQuestListResponse {

        val userId = securityUtils.getCurrentUserId()

        val quests = when (type) {
            Type.ALL -> myQuestRepository.findByIdUserIdAndIdDate(userId, LocalDate.now())
            Type.COMPLETE -> myQuestRepository.findByIdUserIdAndIsComplete(userId, true)
            Type.NOT_COMPLETE -> myQuestRepository.findByIdUserIdAndIsComplete(userId, false)
        }.map {
            GetQuestListResponse.QuestElement(
                id = it.id.questId,
                name = it.name,
                isComplete = it.isComplete
            )
        }

        val progress =
            myQuestRepository.countByIdUserIdAndIsComplete(userId, true) / myQuestRepository.countByIdUserId(userId) * 100

        return GetQuestListResponse(
            quests = quests,
            progress = progress
        )
    }
}