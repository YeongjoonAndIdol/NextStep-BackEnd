package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.response.GetQuestDetailResponse
import com.prject.nextstep.domain.quest.entity.repository.ListRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GetQuestDetailService(
    private val questRepository: QuestRepository,
    private val listRepository: ListRepository
) {

    @Transactional(readOnly = true)
    fun execute(questId: UUID): GetQuestDetailResponse {

        val quest = questRepository.findByIdOrNull(questId) ?: throw QuestNotFoundException
        val lists = listRepository.findByQuestId(questId).map {
            GetQuestDetailResponse.ListElement(
                type = it.type,
                title = it.title,
                content = it.content,
                exp = it.exp
            )
        }

        return GetQuestDetailResponse(
            name = quest.name,
            lists = lists
        )
    }
}