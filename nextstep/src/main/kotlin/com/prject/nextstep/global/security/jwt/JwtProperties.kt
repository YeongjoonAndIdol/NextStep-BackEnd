package com.prject.nextstep.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
class JwtProperties(
    secretKey: String,

    accessExp: Int,

    refreshExp: Int
) {
    val accessExp: Int = accessExp * 1000

    val refreshExp: Int = accessExp * 1000
}