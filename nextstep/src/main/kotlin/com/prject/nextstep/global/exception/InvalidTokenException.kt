package com.prject.nextstep.global.exception

import com.prject.nextstep.global.error.ErrorCode

/**
 *
 * InvalidTokenException
 *
 * @author ljcha
 * @date 2022-11-07
 * @version 1.0.0
 **/
object InvalidTokenException : CustomException(
    ErrorCode.INVALID_TOKEN
)