package com.prject.nextstep.domain.retrospects.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object RetrospectsNotFoundException : CustomException(
    ErrorCode.RETROSPECT_NOT_FOUND
)