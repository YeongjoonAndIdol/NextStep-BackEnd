package com.prject.nextstep.domain.quest.entity.repository

import com.prject.nextstep.domain.quest.entity.Quest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface QuestRepository : JpaRepository<Quest, UUID> {

    @Query(value = "select * from Quest q order by RAND() limit 10", nativeQuery = true)
    fun getRecommend(): List<Quest>

    fun findByNameContaining(name: String): List<Quest>
}