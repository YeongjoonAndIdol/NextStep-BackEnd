package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.request.CreateListRequest
import com.prject.nextstep.domain.quest.entity.repository.ListRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateListService(
    private val questRepository: QuestRepository,
    private val listRepository: ListRepository
) {

    @Transactional
    fun execute(questId: UUID, request: CreateListRequest) {

        val quest = questRepository.findByIdOrNull(questId) ?: throw QuestNotFoundException

        val lists = request.lists.map {
            com.prject.nextstep.domain.quest.entity.List(
                type = it.type,
                title = it.title,
                content = it.content,
                exp = it.exp,
                quest = quest
            )
        }

        listRepository.saveAll(lists)
    }
}