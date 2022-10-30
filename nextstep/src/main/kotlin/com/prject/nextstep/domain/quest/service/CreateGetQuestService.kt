package com.prject.nextstep.domain.quest.service

import com.prject.nextstep.domain.quest.entity.MyQuest
import com.prject.nextstep.domain.quest.entity.MyQuestId
import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.domain.quest.entity.repository.QuestRepository
import com.prject.nextstep.domain.quest.exception.QuestAlreadyGetException
import com.prject.nextstep.domain.quest.exception.QuestNotFoundException
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.UUID

@Service
class CreateGetQuestService(
    private val securityUtils: SecurityUtils,
    private val myQuestRepository: MyQuestRepository,
    private val questRepository: QuestRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(questId: UUID) {

        val userId = securityUtils.getCurrentUserId()
        val quest = questRepository.findByIdOrNull(questId) ?: throw QuestNotFoundException
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException

        if (myQuestRepository.findByIdUserIdAndIdQuestId(userId, questId) != null) {
            throw QuestAlreadyGetException
        }

        val myQuest = MyQuest(
            id = MyQuestId(userId, questId, LocalDate.now()),
            quest = quest,
            user = user,
            name = quest.name
        )

        myQuestRepository.save(myQuest)
    }
}