package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import edu.dongguk.openBadge.dtos.CurriculumDTO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "curriculum")
class Curriculum(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(length = 500, nullable = false)
    val subject: String?,
    @Column(nullable = false)
    val grade: String?,
    @Column(nullable = false)
    val capability: String?,
    @Column(nullable = false)
    val necessary: String?,
    @Column(nullable = false)
    val level: String?,
    @Column(nullable = false)
    val year: Int?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val user: Member? = null

) {
    companion object {
        fun of(curriculumDTO: CurriculumDTO, user: Member): Curriculum = Curriculum(
            id = curriculumDTO.id,
            subject = curriculumDTO.subject,
            grade = curriculumDTO.grade,
            capability = curriculumDTO.capability,
            necessary = curriculumDTO.necessary,
            level = curriculumDTO.level,
            year = curriculumDTO.year,
            user = user
        )
    }
}
