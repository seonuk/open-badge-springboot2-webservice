package edu.dongguk.openBadge.DTOS

import edu.dongguk.openBadge.domain.repository.Member

data class MemberDTO(
        val studentID: String? = null,
        var password: String? = null,
        val major: String? = null,
        val grade: String? = null
) {
    fun toEntity(): Member = Member(
            studentID = this.studentID,
            password = this.password,
            major = this.major,
            grade = this.grade
    )


}
