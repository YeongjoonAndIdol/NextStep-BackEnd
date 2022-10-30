package com.prject.nextstep.global.error

enum class ErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    USER_NOT_FOUND(404, "User Not Found"),
    USER_PASSWORD_NOT_MATCHED(401, "User Password Not Matched"),
    USER_CREDENTIALS_NOT_FOUND(401, "Invalid User Credentials"),

    QUEST_NOT_FOUND(404, "Quest Not Found"),
    QUEST_ALREADY_GET(409, "Quest Already Get"),

    RETROSPECT_NOT_FOUND(404, "Retrospects Not Found"),
    RETROSPECTS_ALREADY_EXISTS(409, "Retrospects Already Exists"),

    BAD_REQUEST(400, "Bad Request");

    override fun status(): Int = status
    override fun message(): String = message
}