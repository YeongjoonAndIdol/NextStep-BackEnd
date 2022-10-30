package com.prject.nextstep.domain.quest

import com.prject.nextstep.domain.quest.dto.Type
import com.prject.nextstep.domain.quest.dto.request.CreateListRequest
import com.prject.nextstep.domain.quest.dto.request.CreateQuestRequest
import com.prject.nextstep.domain.quest.dto.request.UpdateQuestRequest
import com.prject.nextstep.domain.quest.dto.response.GetQuestDetailResponse
import com.prject.nextstep.domain.quest.dto.response.GetQuestListResponse
import com.prject.nextstep.domain.quest.dto.response.GetRecommendQuestsResponse
import com.prject.nextstep.domain.quest.dto.response.SearchQuestResponse
import com.prject.nextstep.domain.quest.service.*
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/quests")
class QuestController(
    private val getQueryListService: GetQuestListService,
    private val getQuestDetailService: GetQuestDetailService,
    private val getRecommendQuestsService: GetRecommendQuestsService,
    private val searchQuestService: SearchQuestService,
    private val createQuestService: CreateQuestService,
    private val createListService: CreateListService,
    private val updateQuestService: UpdateQuestService,
    private val updateRecommendedService: UpdateRecommendedService,
    private val certificationSuccessQuestService: CertificationSuccessQuestService,
    private val createGetQuestService: CreateGetQuestService
) {

    @GetMapping("/list")
    fun getQuestList(@RequestParam type: Type): GetQuestListResponse {
        return getQueryListService.execute(type)
    }

    @GetMapping("/{quest-id}")
    fun getQuestDetail(@PathVariable("quest-id") questId: UUID): GetQuestDetailResponse {
        return getQuestDetailService.execute(questId)
    }

    @GetMapping("/recommend")
    fun recommendedQuest(): GetRecommendQuestsResponse {
        return getRecommendQuestsService.execute()
    }

    @GetMapping("/search")
    fun searchQuest(@RequestParam(required = false) name: String): SearchQuestResponse {
        return searchQuestService.execute(name)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuest(@RequestBody @Valid request: CreateQuestRequest) {
        createQuestService.execute(request)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list/{quest-id}")
    fun createLists(@PathVariable("quest-id") questId: UUID, @RequestBody @Valid request: CreateListRequest) {
        createListService.execute(questId, request)
    }

    @PatchMapping("/{quest-id}")
    fun updateQuest(@PathVariable("quest-id") questId: UUID, @RequestBody @Valid request: UpdateQuestRequest) {
        updateQuestService.execute(questId, request)
    }

    @PatchMapping("/recommend/{quest-id}")
    fun updateRecommended(@PathVariable("quest-id") questId: UUID) {
        updateRecommendedService.execute(questId)
    }

    @PatchMapping("/success/{quest-id}")
    fun certificationSuccess(@PathVariable("quest-id") questId: UUID) {
        certificationSuccessQuestService.execute(questId)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/get/{quest-id}")
    fun getQuest(@PathVariable("quest-id") questId: UUID) {
        createGetQuestService.execute(questId)
    }
}