package com.prject.nextstep.domain.quest.entity.repository

import com.prject.nextstep.domain.quest.entity.LikedQuest
import com.prject.nextstep.domain.quest.entity.LikedQuestId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LikedQuestRepository : JpaRepository<LikedQuest, LikedQuestId> {
    fun findByIdUserId(userId: UUID): List<LikedQuest>
    fun findByIdUserIdAndIdQuestId(userId: UUID, questId: UUID): LikedQuest?
    fun countByIdQuestId(questId: UUID): Long
}