package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import edu.dongguk.openBadge.domain.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.lang.Exception
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.servlet.ServletContext

@Service
class PortfolioNonCurriculumService(
        private val nonCurriculumRepository: NonCurriculumRepository,
        private val memberRepository: MemberRepository,
        private val fileRepository: FileRepository
) {
    fun getNonCurriculumActivities(): List<NonCurriculum> = nonCurriculumRepository.findAll()

    @Transactional
    fun postNonCurriculumActivity(
            nonCurriculumDTO: NonCurriculumDTO,
            customUser: CustomUser
    ): NonCurriculum {
        val studentId: String? = customUser.studentID
        val user: Member? = memberRepository.findByStudentID(customUser.studentID)
        val nonCurriculum: NonCurriculum = nonCurriculumDTO.toEntity()

        user?.let{ nonCurriculum.mappingUser(it) } ?: throw Exception()

        nonCurriculumDTO.files?.let {
            for (file in it) {
                val d: Date = Date()
                val fileName: String? = file.originalFilename
                val uploadPath: String = "/Users/seonuk/Downloads/openBadge/src/main/resources/uploads/" + customUser.studentID
                val f: File = File(uploadPath)

                if (!f.exists()) {
                    f.mkdir()
                }

                val path: String = uploadPath + "/" + fileName + d.time
                val userFile: UserFile = UserFile(
                        fileName = fileName,
                        filePath = path,
                        nonCurriculum = nonCurriculum
                )
                fileRepository.save(userFile)

                if (!fileName.isNullOrBlank()){
                    file.transferTo(File(path))
                }
            }
        }




        return nonCurriculumRepository.save(nonCurriculum)
    }
    fun getOne(nonCurriculumId: Long): NonCurriculum? = nonCurriculumRepository.findByIdOrNull(nonCurriculumId)

    @Transactional
    fun modifyNonCurriculumActivity(nonCurriculumId: Long, nonCurriculumDTO: NonCurriculumDTO): NonCurriculum? {
        val c: NonCurriculum? = nonCurriculumRepository.findByIdOrNull(nonCurriculumId)

        return c?.update(nonCurriculumDTO)
    }

    fun deleteNonCurriculumActivity(nonCurriculumId: Long) = nonCurriculumRepository.deleteById(nonCurriculumId)


}
