package edu.dongguk.openBadge

import edu.dongguk.openBadge.config.FileUploadProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.nio.file.Path
import java.nio.file.Paths

@SpringBootApplication
class OpenBadgeApplication
fun main(args: Array<String>) {
	runApplication<OpenBadgeApplication>(*args)

}
