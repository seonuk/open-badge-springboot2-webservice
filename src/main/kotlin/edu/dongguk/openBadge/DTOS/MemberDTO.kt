package edu.dongguk.openBadge.DTOS

import edu.dongguk.openBadge.domain.repository.Member

data class MemberDTO(
        val studentID: String? = null,
        val name: String? = null,
        var password: String? = null,
        val major: String? = null,
        val grade: String? = null
) {
    fun toEntity(): Member = Member(
            studentID = this.studentID,
            name = this.name,
            password = this.password,
            major = this.major,
            grade = this.grade
    )




}
