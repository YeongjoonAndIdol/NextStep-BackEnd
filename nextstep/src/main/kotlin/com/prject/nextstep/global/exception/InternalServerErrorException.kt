package com.prject.nextstep.global.exception

import com.prject.nextstep.global.error.ErrorCode

object InternalServerErrorException : CustomException(
    ErrorCode.INTERNAL_SERVER_ERROR
) {
}