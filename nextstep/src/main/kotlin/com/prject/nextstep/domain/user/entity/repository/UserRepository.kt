package com.prject.nextstep.domain.user.entity.repository

import com.prject.nextstep.domain.user.dto.RankingResponseDto
import com.prject.nextstep.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByAccountId(accountId: String): User?
    fun findByEmail(email: String): User?

    @Query("select new map(u.id, rank()) over (order by u.level desc) from User u", nativeQuery = true)
    fun queryOnlyRanking(): Map<UUID, Int>


    @Query(name = "queryRanking", nativeQuery = true)
    fun queryRanking(): List<RankingResponseDto>
}