package com.prject.nextstep.domain.retrospects

import com.prject.nextstep.domain.retrospects.dto.request.CreateRetrospectsRequest
import com.prject.nextstep.domain.retrospects.dto.request.UpdateRetrospectsRequest
import com.prject.nextstep.domain.retrospects.dto.response.GetRetrospectsResponse
import com.prject.nextstep.domain.retrospects.service.CreateRetrospectsService
import com.prject.nextstep.domain.retrospects.service.DeleteRetrospectsService
import com.prject.nextstep.domain.retrospects.service.GetRetrospectsService
import com.prject.nextstep.domain.retrospects.service.UpdateRetrospectsService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.UUID
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/retrospects")
class RetrospectsController(
    private val createRetrospectsService: CreateRetrospectsService,
    private val updateRetrospectsService: UpdateRetrospectsService,
    private val getRetrospectsService: GetRetrospectsService,
    private val deleteRetrospectsService: DeleteRetrospectsService
) {

    @GetMapping("/{date}")
    fun getRetrospects(@PathVariable date: LocalDate): GetRetrospectsResponse {
        return getRetrospectsService.execute(date)
    }

    @PostMapping
    fun createRetrospects(@RequestBody @Valid request: CreateRetrospectsRequest) {
        createRetrospectsService.execute(request.content)
    }

    @PatchMapping("/{retrospects-id}")
    fun updateRetrospects(@PathVariable("retrospects-id") retrospectsId: UUID, @RequestBody @Valid request: UpdateRetrospectsRequest) {
        updateRetrospectsService.execute(retrospectsId, request.content)
    }

    @DeleteMapping("/{retrospects-id}")
    fun deleteRetrospects(@PathVariable("retrospects-id") retrospectsId: UUID) {
        deleteRetrospectsService.execute(retrospectsId)
    }
}