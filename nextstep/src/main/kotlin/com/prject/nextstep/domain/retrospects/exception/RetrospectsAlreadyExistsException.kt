package com.prject.nextstep.domain.retrospects.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object RetrospectsAlreadyExistsException: CustomException(
    ErrorCode.RETROSPECTS_ALREADY_EXISTS
)