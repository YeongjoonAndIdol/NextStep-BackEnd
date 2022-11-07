package com.prject.nextstep.global.exception

import com.prject.nextstep.global.error.ErrorCode

/**
 *
 * UnexpectedTokenException
 *
 * @author ljcha
 * @date 2022-11-07
 * @version 1.0.0
 **/
object UnexpectedTokenException : CustomException(
    ErrorCode.UNEXPECTED_TOKEN
)