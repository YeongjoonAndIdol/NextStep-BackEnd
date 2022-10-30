package com.prject.nextstep.domain.retrospects.service

import com.prject.nextstep.domain.retrospects.entity.Retrospects
import com.prject.nextstep.domain.retrospects.entity.repository.RetrospectsRepository
import com.prject.nextstep.domain.retrospects.exception.RetrospectsNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateRetrospectsService(
    private val retrospectsRepository: RetrospectsRepository
) {

    @Transactional
    fun execute(retrospectsId: UUID, content: String) {
        val retrospects = retrospectsRepository.findByIdOrNull(retrospectsId) ?: throw RetrospectsNotFoundException

        retrospectsRepository.save(
            Retrospects(
                id = retrospects.id,
                content = content,
                date = retrospects.date,
                user = retrospects.user
            )
        )
    }
}