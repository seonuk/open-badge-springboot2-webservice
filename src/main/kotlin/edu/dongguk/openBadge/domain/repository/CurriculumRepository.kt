package edu.dongguk.openBadge.domain.repository

import org.springframework.data.jpa.repository.JpaRepository

interface CurriculumRepository : JpaRepository<Curriculum, Long> {
    fun findAllByUser(
        user: Member
    ): List<Curriculum>

    fun findByIdAndUser(
        Id: Long,
        user: Member
    ): Curriculum?

    fun deleteByIdAndUser(
        Id: Long,
        user: Member
    )
}
