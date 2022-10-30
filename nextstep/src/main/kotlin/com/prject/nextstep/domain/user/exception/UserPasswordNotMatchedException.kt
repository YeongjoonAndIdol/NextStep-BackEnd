package com.prject.nextstep.domain.user.exception

import com.prject.nextstep.global.error.ErrorCode
import com.prject.nextstep.global.exception.CustomException

object UserPasswordNotMatchedException : CustomException(
    ErrorCode.USER_PASSWORD_NOT_MATCHED
)