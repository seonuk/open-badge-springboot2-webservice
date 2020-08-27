package edu.dongguk.openBadge.dtos

import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.NonCurriculum

data class NonCurriculumDTO(
    val id: Long? = null,
    val capability: String,
    val activityName: String,
    val necessary: String,
    val level: String,
    val division: String,
    val format: String,
    val field: String,
    val content: String,
    val startDay: String,
    val endDay: String,
    val totalTime: Int,
    val participants: Int,
    val programLevel: Int,
    val selfEvaluation: Int
) {

    fun toEntity(member: Member): NonCurriculum = NonCurriculum(
        id = this.id,
        capability = this.capability,
        activityName = this.activityName,
        necessary = this.necessary,
        level = this.level,
        division = this.division,
        format = this.format,
        field = this.field,
        content = this.content,
        start_day = this.startDay,
        end_day = this.endDay,
        total_time = this.totalTime,
        participants = this.participants,
        program_level = this.programLevel,
        self_evaluation = selfEvaluation,
        user = member
    )
}
