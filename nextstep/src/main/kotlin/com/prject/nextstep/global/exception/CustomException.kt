package com.prject.nextstep.global.exception

import com.prject.nextstep.global.error.ErrorProperty

abstract class CustomException(
    val errorProperty: ErrorProperty
): RuntimeException() {
    override fun fillInStackTrace() = this
}