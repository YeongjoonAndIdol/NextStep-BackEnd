package com.prject.nextstep.domain.retrospects.service

import com.prject.nextstep.domain.retrospects.entity.repository.RetrospectsRepository
import com.prject.nextstep.domain.retrospects.exception.RetrospectsNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteRetrospectsService(
    private val retrospectsRepository: RetrospectsRepository
) {

    fun execute(retrospectsId: UUID) {

        val retrospects = retrospectsRepository.findByIdOrNull(retrospectsId) ?: throw RetrospectsNotFoundException

        retrospectsRepository.delete(retrospects)
    }
}