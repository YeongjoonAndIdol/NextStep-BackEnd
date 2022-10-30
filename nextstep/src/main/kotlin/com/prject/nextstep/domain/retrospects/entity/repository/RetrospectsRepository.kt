package com.prject.nextstep.domain.retrospects.entity.repository

import com.prject.nextstep.domain.retrospects.entity.Retrospects
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.UUID

interface RetrospectsRepository: JpaRepository<Retrospects, UUID> {
    fun findByUserIdAndDate(userId: UUID, date: LocalDate): Retrospects?
}