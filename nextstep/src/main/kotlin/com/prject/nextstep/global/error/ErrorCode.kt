package com.prject.nextstep.global.error

enum class ErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    BAD_REQUEST(404, "Bad Request");

    override fun status(): Int = status
    override fun message(): String = message
}