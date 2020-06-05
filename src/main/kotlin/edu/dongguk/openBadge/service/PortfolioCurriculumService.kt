package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import edu.dongguk.openBadge.domain.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.*

@Service
class PortfolioCurriculumService(
        private val curriculumRepository: CurriculumRepository,
        private val memberRepository: MemberRepository
) {
    fun getCurriculumActivities(): List<Curriculum> = curriculumRepository.findAll()

    @Transactional
    fun postCurriculumActivity(
            curriculum: Curriculum,
            customUser: CustomUser
    ): Long? {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)

        member?.let { curriculum.mappingUser(it) } ?: throw Exception()

        return  curriculumRepository.save(curriculum).id
    }

    fun getOne(
            curriculumId: Long
    ): Curriculum? = curriculumRepository.findByIdOrNull(curriculumId)


    @Transactional
    fun modifyCurriculumActivity(curriculumId: Long, curriculum: CurriculumDTO): Curriculum? {
        val c: Curriculum? = curriculumRepository.findByIdOrNull(curriculumId)

        return c?.update(curriculum)
    }

    fun deleteCurriculumActivity(curriculumId: Long): Unit = curriculumRepository.deleteById(curriculumId)

}
