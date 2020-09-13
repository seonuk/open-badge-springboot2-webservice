package edu.dongguk.openBadge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class OpenBadgeApplication

fun main(args: Array<String>) {
    runApplication<OpenBadgeApplication>(*args)
}
