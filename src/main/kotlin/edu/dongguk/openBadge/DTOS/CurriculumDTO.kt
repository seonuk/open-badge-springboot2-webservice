package edu.dongguk.openBadge.DTOS

data class CurriculumDTO(
    val subject: String? = null,
    val grade: String? = null,
    val capability: String? = null,
    val necessary: String? = null,
    val level: String? = null,
    val year: Int? = null,
    val user: MemberDTO? = null
)
