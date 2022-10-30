package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.domain.user.dto.response.MyPageResponse
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.domain.user.exception.UserNotFoundException
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class GetMyPageService(
    private val securityUtils: SecurityUtils,
    private val userRepository: UserRepository,
    private val myQuestRepository: MyQuestRepository
    ) {

    @Transactional(readOnly = true)
    fun execute(): MyPageResponse {

        val userId = securityUtils.getCurrentUserId()
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException

        // TODO Query 개선
        val ranking = userRepository.queryOnlyRanking().get(userId)

        val myQuest = myQuestRepository.findByIdUserIdAndIdDate(userId, LocalDate.now()).map {
            MyPageResponse.MyQuestElement(
                id = it.id.questId,
                name = it.name
            )
        }

        return MyPageResponse(
            name = user.name,
            level = user.level,
            exp = user.exp,
            walkCount = user.walkCount,
            ranking = ranking ?: 9999,
            myRoutine = myQuest
        )
    }
}