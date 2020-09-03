package edu.dongguk.openBadge.domain.repository

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByStudentID(
        studentId: String?
    ): Member?
}
