package com.prject.nextstep.domain.user.service

import com.prject.nextstep.domain.user.dto.response.GetHallOfFameResponse
import com.prject.nextstep.domain.user.entity.repository.UserRepository
import com.prject.nextstep.global.security.SecurityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetHallOfFameService(
    val securityUtils: SecurityUtils,
    val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun execute(): GetHallOfFameResponse {

        val userId = securityUtils.getCurrentUserId()

        val ranking = userRepository.queryRanking()

        val userRanking  = ranking.map {
            GetHallOfFameResponse.UserRankingElement(
                id = it.id,
                name = it.name,
                ranking = it.ranking,
                level = it.level
            )
        }

        val myRanking = ranking.first {
            it.id == userId
        }

        return GetHallOfFameResponse(
            userRanking = userRanking,
            myRanking = GetHallOfFameResponse.MyRankingElement(
                name = myRanking.name,
                ranking = myRanking.ranking,
                level = myRanking.level
            )
        )
    }
}