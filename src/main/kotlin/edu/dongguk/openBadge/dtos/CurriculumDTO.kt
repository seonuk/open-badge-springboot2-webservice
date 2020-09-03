package edu.dongguk.openBadge.dtos

import edu.dongguk.openBadge.domain.repository.Curriculum
import edu.dongguk.openBadge.domain.repository.Member

data class CurriculumDTO(
    val id: Long? = null,
    val subject: String? = null,
    val grade: String? = null,
    val capability: String? = null,
    val necessary: String? = null,
    val level: String? = null,
    val year: Int? = null
) {
    fun toEntity(member: Member): Curriculum = Curriculum(
        id = this.id,
        subject = this.subject,
        grade = this.grade,
        capability = this.capability,
        necessary = this.necessary,
        level = this.level,
        year = this.year,
        user = member
    )
}
