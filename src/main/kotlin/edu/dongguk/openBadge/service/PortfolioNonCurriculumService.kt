package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import edu.dongguk.openBadge.repository.NonCurriculum
import edu.dongguk.openBadge.repository.NonCurriculumRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PortfolioNonCurriculumService(
        private val nonCurriculumRepository: NonCurriculumRepository
) {
    fun getNonCurriculumActivities(): List<NonCurriculum> = nonCurriculumRepository.findAll()

    fun postNonCurriculumActivity(
            nonCurriculum: NonCurriculum
    ): NonCurriculum = nonCurriculumRepository.save(nonCurriculum)

    fun getOne(nonCurriculumId: Long): NonCurriculum? = nonCurriculumRepository.getOne(nonCurriculumId)

    @Transactional
    fun modifyNonCurriculumActivity(nonCurriculumId: Long, nonCurriculumDTO: NonCurriculumDTO): NonCurriculum? {
        val c: NonCurriculum? = nonCurriculumRepository.findByIdOrNull(nonCurriculumId)

        return c?.update(nonCurriculumDTO)
    }


}
