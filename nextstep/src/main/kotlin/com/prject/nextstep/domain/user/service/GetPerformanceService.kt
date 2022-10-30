package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.quest.entity.repository.ListRepository
import com.prject.nextstep.domain.quest.entity.repository.MyQuestRepository
import com.prject.nextstep.domain.user.dto.response.GetPerformanceResponse
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
class GetPerformanceService(
    private val myQuestRepository: MyQuestRepository,
    private val securityUtils: SecurityUtils,
    private val listRepository: ListRepository
) {

    @Transactional(readOnly = true)
    fun execute(): GetPerformanceResponse {
        val userId = securityUtils.getCurrentUserId();

        val now = LocalDate.now()
        val nowDay = now.dayOfWeek.value

        val weekAchievement = myQuestRepository.findByIdUserIdAndIdDateBetween(
            userId,
            now.minusDays((nowDay - 1).toLong()),
            now.plusDays((7 - nowDay).toLong())
        ).map {
            it.isComplete
        }

        val lists = myQuestRepository.findByIdUserIdAndIdDate(userId, now).flatMap {
            listRepository.findByQuestId(it.id.questId)
        }

        val completedQuests = lists.map {
            GetPerformanceResponse.CompletedQuestElement(
                type = it.type,
                title = it.title,
                content = it.content,
                exp = it.exp
            )
        }

        return GetPerformanceResponse(
            weekAchievement = weekAchievement,
            completedQuests = completedQuests
        )


    }
}