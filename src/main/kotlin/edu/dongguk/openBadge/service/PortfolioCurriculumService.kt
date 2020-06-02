package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import edu.dongguk.openBadge.domain.repository.Curriculum
import edu.dongguk.openBadge.domain.repository.CurriculumRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PortfolioCurriculumService(
        private val curriculumRepository: CurriculumRepository
) {
    fun getCurriculumActivities(): List<Curriculum> = curriculumRepository.findAll()

    fun postCurriculumActivity(curriculum: Curriculum): Curriculum = curriculumRepository.save(curriculum)

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
