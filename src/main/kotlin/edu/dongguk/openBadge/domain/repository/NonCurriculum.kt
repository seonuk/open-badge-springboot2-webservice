package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import edu.dongguk.openBadge.DTOS.NonCurriculumDTO
import javax.persistence.*

@Entity(name = "non_curriculum")
class NonCurriculum(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var capability: String,
        @Column(length = 500, nullable = false)
        var activityName: String,
        @Column(nullable = false)
        var necessary: String,
        @Column(nullable = false)
        var level: String,
        @Column(nullable = false)
        var division: String,
        @Column(nullable = false)
        var format: String,
        @Column(nullable = false)
        var field: String,
        @Column(nullable = false)
        var content: String,
        @Column(nullable = false)
        var start_day: String,
        @Column(nullable = false)
        var end_day: String,
        @Column(nullable = false)
        var total_time: Int,
        @Column(nullable = false)
        var participants: Int,
        @Column(nullable = false)
        var program_level: Int,
        @Column(nullable = false)
        var self_evaluation: Int,
        @ManyToOne
        @JoinColumn(name="user_id")
        @JsonBackReference
        var user: Member? = null,

        @OneToMany(mappedBy = "nonCurriculum")
        @JsonManagedReference
        val files: MutableList<UserFile>? = null

) {

    fun mappingUser(user: Member?) {
        this.user = user
    }
    fun update(noncurriculumDTO: NonCurriculumDTO): NonCurriculum {
        this.capability = noncurriculumDTO.capability
        this.activityName = noncurriculumDTO.activityName
        this.necessary = noncurriculumDTO.necessary
        this.level = noncurriculumDTO.level
        this.division = noncurriculumDTO.division
        this.format = noncurriculumDTO.format
        this.field = noncurriculumDTO.field
        this.content = noncurriculumDTO.content
        this.start_day = noncurriculumDTO.start_day
        this.end_day = noncurriculumDTO.end_day
        this.total_time = noncurriculumDTO.total_time
        this.participants = noncurriculumDTO.participants
        this.program_level = noncurriculumDTO.program_level
        this.self_evaluation = noncurriculumDTO.self_evaluation

        return this
    }


}