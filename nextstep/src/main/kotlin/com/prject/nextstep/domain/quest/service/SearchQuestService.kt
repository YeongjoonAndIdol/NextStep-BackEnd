package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.response.SearchQuestResponse
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchQuestService(
    private val questRepository: QuestRepository
) {

    @Transactional(readOnly = true)
    fun execute(name: String): SearchQuestResponse {

        val quests= questRepository.findByNameContaining(name).map {
            SearchQuestResponse.QuestElement(
                id = it.id,
                name = it.name
            )
        }

        return SearchQuestResponse(
            quests = quests
        )
    }
}