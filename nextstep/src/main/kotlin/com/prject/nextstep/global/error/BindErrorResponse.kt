package com.prject.nextstep.global.error

/**
 *
 * BindErrorResponse
 *
 * @author ljcha
 * @date 2022-11-07
 * @version 1.0.0
 **/
data class BindErrorResponse(
    val status: Int,
    val fieldError: List<Map<String, String?>>
)