package com.prject.nextstep.domain.user.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object UserNotFoundException : CustomException(
    ErrorCode.USER_NOT_FOUND
)