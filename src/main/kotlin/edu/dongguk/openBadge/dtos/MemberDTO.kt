package edu.dongguk.openBadge.dtos

import edu.dongguk.openBadge.domain.Role
import edu.dongguk.openBadge.domain.repository.Member

data class MemberDTO(
    val id: Long? = null,
    val studentID: String? = null,
    val name: String? = null,
    val password: String? = null,
    val major: String? = null,
    val grade: String? = null,
    val authority: String? = null
) {

    fun toEntity(): Member = Member(
        id = this.id,
        studentID = this.studentID,
        name = this.name,
        password = this.password,
        major = this.major,
        grade = this.grade,
        authority = Role.MEMBER
    )
}
