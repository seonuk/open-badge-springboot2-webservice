package edu.dongguk.openBadge.config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "file")
data class FileUploadProperties(
    val uploadDir: String = "./src/main/resources/uploads"
)
