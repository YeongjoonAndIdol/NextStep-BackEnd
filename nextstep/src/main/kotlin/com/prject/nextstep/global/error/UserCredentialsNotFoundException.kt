package com.prject.nextstep.global.error

import com.prject.nextstep.global.exception.CustomException

object UserCredentialsNotFoundException : CustomException(
    ErrorCode.USER_CREDENTIALS_NOT_FOUND
)