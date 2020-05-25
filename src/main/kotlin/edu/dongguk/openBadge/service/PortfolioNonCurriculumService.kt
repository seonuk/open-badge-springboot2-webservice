package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import edu.dongguk.openBadge.repository.NonCurriculum
import edu.dongguk.openBadge.repository.NonCurriculumRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class PortfolioNonCurriculumService(
        private val nonCurriculumRepository: NonCurriculumRepository
) {
    fun getNonCurriculumActivities(): List<NonCurriculum> = nonCurriculumRepository.findAll()

    fun postNonCurriculumActivity(
            nonCurriculumDTO: NonCurriculumDTO
    ): NonCurriculum {

        if (!nonCurriculumDTO.files.isNullOrEmpty()) {
            for (file in nonCurriculumDTO.files!!) {
                val fileName: String? = file.originalFilename
                file.transferTo(File("/Users/seonuk/Downloads/openBadge/src/main/resources/files/$fileName"))
                // file save
                println("file upload")
            }
        }

        return nonCurriculumRepository.save(nonCurriculumDTO.toEntity())
    }
    fun getOne(nonCurriculumId: Long): NonCurriculum? = nonCurriculumRepository.getOne(nonCurriculumId)

    @Transactional
    fun modifyNonCurriculumActivity(nonCurriculumId: Long, nonCurriculumDTO: NonCurriculumDTO): NonCurriculum? {
        val c: NonCurriculum? = nonCurriculumRepository.findByIdOrNull(nonCurriculumId)

        return c?.update(nonCurriculumDTO)
    }


}