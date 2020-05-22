package edu.dongguk.openBadge.DTOS


data class CurriculumDTO(
        val subject: String? = null,
        val grade: String? = null,
        val capability: Int? = null,
        val necessary: String? = null,
        val level: String? = null,
        val year: Int? = null
)