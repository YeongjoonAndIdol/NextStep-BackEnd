package com.prject.nextstep.domain.quest.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object QuestAlreadyGetException : CustomException(
    ErrorCode.QUEST_ALREADY_GET
)