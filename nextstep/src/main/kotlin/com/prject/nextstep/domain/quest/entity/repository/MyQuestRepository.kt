package com.prject.nextstep.domain.quest.entity.repository

import com.prject.nextstep.domain.quest.entity.MyQuest
import com.prject.nextstep.domain.quest.entity.MyQuestId
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*
import kotlin.collections.List

interface MyQuestRepository : JpaRepository<MyQuest, MyQuestId> {
    fun findByIdUserIdAndIdDate(userId: UUID, date: LocalDate): List<MyQuest>
    fun findByIdUserIdAndIdDateBetween(userId: UUID, start: LocalDate, end: LocalDate): List<MyQuest>
    fun findByIdUserIdAndIsComplete(userId: UUID, complete: Boolean): List<MyQuest>
    fun findByIdUserIdAndIdQuestId(userId: UUID, questId: UUID): MyQuest?

    fun countByIdUserIdAndIsComplete(userId: UUID, complete: Boolean): Long
    fun countByIdUserId(userId: UUID): Long
}