package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.request.UpdateQuestRequest
import com.prject.nextstep.domain.quest.entity.Quest
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateQuestService(
    private val questRepository: QuestRepository
) {

    @Transactional
    fun execute(questId: UUID, request: UpdateQuestRequest) {

        questRepository.findByIdOrNull(questId) ?: throw QuestNotFoundException

        val quest = Quest(
            name = request.title,
            period = request.period,
            time = request.time,
            content = request.content,
            schoolType = request.schoolType
        )

        questRepository.save(quest)
    }
}