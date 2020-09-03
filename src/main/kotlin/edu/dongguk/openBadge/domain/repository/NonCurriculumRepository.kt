package edu.dongguk.openBadge.domain.repository

import org.springframework.data.jpa.repository.JpaRepository

interface NonCurriculumRepository : JpaRepository<NonCurriculum, Long> {

    fun findAllByUser(
        user: Member
    ): List<NonCurriculum>

    fun findByIdAndUser(
        id: Long,
        user: Member
    ): NonCurriculum?

    fun deleteByIdAndUser(
        id: Long,
        user: Member
    )
}
