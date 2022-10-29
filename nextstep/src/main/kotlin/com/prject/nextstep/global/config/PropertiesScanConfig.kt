package com.prject.nextstep.global.config

import com.prject.nextstep.global.security.jwt.JwtProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class
    ]
)
@Configuration
class PropertiesScanConfig