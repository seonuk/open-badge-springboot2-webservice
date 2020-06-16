package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.config.FileUploadProperties
import edu.dongguk.openBadge.domain.repository.FileRepository
import edu.dongguk.openBadge.domain.repository.NonCurriculum
import edu.dongguk.openBadge.domain.repository.UserFile
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils.cleanPath
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.Date

@Service
class FileUploadDownloadService(
    private val fileUploadProperties: FileUploadProperties,
    private val fileRepository: FileRepository
) {
    fun storeFile(
        file: MultipartFile,
        studentId: String,
        nonCurriculum: NonCurriculum
    ): String? {
        val fileLocation = Paths.get(fileUploadProperties.uploadDir + "/$studentId").toAbsolutePath().normalize()
        Files.createDirectories(fileLocation)
        // 에외 처리

        val fileName = file.originalFilename?.let { cleanPath(Date().time.toString() + it) } ?: throw Exception()

        val targetLocation = fileLocation.resolve(fileName)
        val userFile: UserFile = UserFile(
            fileName = fileName,
            fileDownloadURI = targetLocation.toString(),
            fileSize = file.size,
            nonCurriculum = nonCurriculum
        )

        fileRepository.save(userFile)

        Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)

        return fileName
    }
}
