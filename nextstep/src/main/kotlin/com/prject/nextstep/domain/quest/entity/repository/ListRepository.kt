package com.prject.nextstep.domain.quest.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ListRepository : JpaRepository<com.prject.nextstep.domain.quest.entity.List, UUID> {
    fun findByQuestId(questId: UUID): List<com.prject.nextstep.domain.quest.entity.List>
}