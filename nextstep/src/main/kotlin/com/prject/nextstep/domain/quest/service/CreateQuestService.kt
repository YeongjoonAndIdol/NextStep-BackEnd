package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.dto.request.CreateQuestRequest
import com.prject.nextstep.domain.quest.entity.MyQuest
import com.prject.nextstep.domain.quest.entity.MyQuestId
import com.prject.nextstep.domain.quest.entity.Quest
import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class CreateQuestService(
    private val securityUtils: SecurityUtils,
    private val questRepository: QuestRepository,
    private val myQuestRepository: MyQuestRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(request: CreateQuestRequest) {

        val userId = securityUtils.getCurrentUserId()

        val quest = Quest(
            name = request.title,
            period = request.period,
            time = request.time,
            content = request.content,
            schoolType = request.schoolType
        )

        val questResponse = questRepository.save(quest)

        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException

        val myQuest = MyQuest(
            id = MyQuestId(userId, questResponse.id, LocalDate.now()),
            user = user,
            quest = questResponse,
            name = quest.name
        )

        myQuestRepository.save(myQuest)
    }
}