package edu.dongguk.openBadge.DTOS

import edu.dongguk.openBadge.domain.repository.NonCurriculum
import org.springframework.web.multipart.MultipartFile

data class NonCurriculumDTO(
        var capability: String,
        var activityName: String,
        var necessary: String,
        var level: String,
        var division: String,
        var format: String,
        var field: String,
        var content: String,
        var start_day: String,
        var end_day: String,
        var total_time: Int,
        var participants: Int,
        var program_level: Int,
        var self_evaluation: Int,
        var files: List<MultipartFile>?
) {

    fun toEntity(): NonCurriculum = NonCurriculum(
            capability = this.capability,
            activityName = this.activityName,
            necessary = this.necessary,
            level = this.level,
            division = this.division,
            format = this.format,
            field = this.field,
            content = this.content,
            start_day = this.start_day,
            end_day = this.end_day,
            total_time = this.total_time,
            participants = this.participants,
            program_level = this.program_level,
            self_evaluation = self_evaluation
    )

}