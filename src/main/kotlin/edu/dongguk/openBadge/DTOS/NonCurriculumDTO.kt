package edu.dongguk.openBadge.DTOS

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
        var self_evaluation: Int
) {
}