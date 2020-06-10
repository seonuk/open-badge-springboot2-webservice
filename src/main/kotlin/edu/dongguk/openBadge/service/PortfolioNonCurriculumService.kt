package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.dtos.NonCurriculumDTO
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.domain.repository.FileRepository
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import edu.dongguk.openBadge.domain.repository.NonCurriculum
import edu.dongguk.openBadge.domain.repository.NonCurriculumRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PortfolioNonCurriculumService(
    private val nonCurriculumRepository: NonCurriculumRepository,
    private val memberRepository: MemberRepository,
    private val fileRepository: FileRepository
) {
    fun getNonCurriculumActivities(customUser: CustomUser): List<NonCurriculum> {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)

        // if don't have curriculumList, return []
        return member?.let { nonCurriculumRepository.findAllByUser(member) } ?: throw Exception()
    }

    fun getOne(
        nonCurriculumId: Long,
        customUser: CustomUser
    ): NonCurriculum {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)

        return member?.let {
            nonCurriculumRepository.findByIdAndUser(
                nonCurriculumId, member
            ) ?: throw Exception() // NotFoundNoncurriculumException
        } ?: throw Exception() // NotFoundUserException
    }

    @Transactional
    fun postNonCurriculumActivity(
        nonCurriculumDTO: NonCurriculumDTO,
        customUser: CustomUser
    ): NonCurriculum {
        val studentId: String? = customUser.studentID
        val user: Member? = memberRepository.findByStudentID(customUser.studentID)

        val nonCurriculum: NonCurriculum = user
            ?.let { nonCurriculumDTO.toEntity(member = it) } ?: throw Exception() // NotFindUserException
        // file uploads logic
        try {
            return nonCurriculumRepository.save(nonCurriculum)
        } catch (e: Exception) {
            // can'tSaveNonCurriculum
            throw e
        }
    }

    @Transactional
    fun modifyNonCurriculumActivity(
        nonCurriculumId: Long,
        nonCurriculumDTO: NonCurriculumDTO,
        customUser: CustomUser
    ): NonCurriculum {
        val user: Member? = memberRepository.findByStudentID(customUser.studentID)

        val nonCurriculum: NonCurriculum = user?.let {
                nonCurriculumDTO.copy(
                    id = nonCurriculumId
                ).toEntity(member = it)
        } ?: throw Exception() // Can't create noncurriculum Exception

        try {
            return nonCurriculumRepository.save(nonCurriculum)
        } catch (e: Exception) {
            // can'tSaveNonCurriculum
            throw e
        }
    }

    fun deleteNonCurriculumActivity(nonCurriculumId: Long, customUser: CustomUser) {
        val member: Member? = memberRepository.findByStudentID(customUser.studentID)

        try {
            member?.let {
                nonCurriculumRepository.deleteByIdAndUser(nonCurriculumId, it)
            } ?: throw Exception() // notFoundUserException
        } catch (e: Exception) {
            // can'tDeleteNonCurriculum
            throw e
        }
    }
}
