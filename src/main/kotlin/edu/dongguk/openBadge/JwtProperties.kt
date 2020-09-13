package edu.dongguk.openBadge

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val SECRET: String,
    val EXPIRATION_TIME: Int, // validation: 1 day
    val TOKEN_PREFIX: String,
    val HEADER_STRING: String
)
