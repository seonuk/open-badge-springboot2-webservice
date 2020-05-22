package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.repository.Curriculum
import edu.dongguk.openBadge.repository.CurriculumRepository
import org.springframework.stereotype.Service

@Service
class PortfolioCurriculumService(
        private val curriculumRepository: CurriculumRepository
) {
    fun getCurriculumActivities(): List<Curriculum>? {
        return curriculumRepository.findAll()
    }

    fun postCurriculumActivities(curriculum: Curriculum): Curriculum? {
        return curriculumRepository.save(curriculum)
    }

}
