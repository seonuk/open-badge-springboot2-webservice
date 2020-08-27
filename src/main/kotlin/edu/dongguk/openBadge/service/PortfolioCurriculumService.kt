package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.dtos.CurriculumDTO
import edu.dongguk.openBadge.domain.repository.Curriculum
import edu.dongguk.openBadge.domain.repository.CurriculumRepository
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import edu.dongguk.openBadge.exception.InternalServerException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PortfolioCurriculumService(
    private val curriculumRepository: CurriculumRepository,
    private val memberRepository: MemberRepository
) {
    fun getCurriculumActivities(customUser: CustomUser): List<Curriculum> {
        val member: Member = memberRepository.findByStudentID(customUser.studentID)
            ?: throw InternalServerException("Not Found User", 101) // NotFoundUserException

        // if don't have curriculumList, return []
        return curriculumRepository.findAllByUser(member)
    }

    fun getOne(
        curriculumId: Long,
        customUser: CustomUser
    ): Curriculum {
        val member: Member = memberRepository.findByStudentID(customUser.studentID)
            ?: throw InternalServerException("Not Found User", 101) // NotFoundUserException

        return curriculumRepository.findByIdAndUser(curriculumId, member)
                ?: throw InternalServerException("Not Found Curriculum", 103) // NotFoundCurriculumException
    }

    @Transactional
    fun postCurriculumActivity(
        curriculumDTO: CurriculumDTO,
        customUser: CustomUser
    ): Curriculum {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)
        val curriculum: Curriculum = member?.let { Curriculum.of(curriculumDTO, it) } ?: throw Exception()

        try {
            return curriculumRepository.save(curriculum)
        } catch (e: Exception) {
            // handler  Can'tSaveException
            throw e
        }
    }

    @Transactional
    fun modifyCurriculumActivity(
        curriculumId: Long,
        curriculumDTO: CurriculumDTO,
        customUser: CustomUser
    ): Curriculum {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)
        val curriculum: Curriculum =
            member?.let {
                curriculumDTO.copy(
                id = curriculumId
            ).toEntity(member = it) } ?: throw Exception()

        try {
            val responseCurriculum = curriculumRepository.save(curriculum)
            return responseCurriculum
        } catch (e: Exception) {
            // handler  Can'tSaveException
            throw e
        }
    }

    fun deleteCurriculumActivity(curriculumId: Long, customUser: CustomUser) {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)

        member?.let {
                try {
                    curriculumRepository.deleteByIdAndUser(curriculumId, it)
                } catch (e: Exception) {
                    // handler  Can'tDeleteNonCurriculum
                    throw e
                }
            } ?: throw Exception() // NotFoundUserException
    }
}
