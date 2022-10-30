package com.prject.nextstep.domain.quest.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object QuestNotFoundException : CustomException(
    ErrorCode.QUEST_NOT_FOUND
)