package edu.dongguk.openBadge.domain.repository

import edu.dongguk.openBadge.DTOS.CurriculumDTO
import javax.persistence.*

@Entity(name = "curriculum")
class Curriculum(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(length = 500, nullable = false)
        var subject: String,
        @Column(nullable = false)
        var grade: String,
        @Column(nullable = false)
        var capability: String,
        @Column(nullable = false)
        var necessary: String,
        @Column(nullable = false)
        var level: String,
        @Column(nullable = false)
        var year: Int
) {
        fun update(curriculum: CurriculumDTO): Curriculum {
                if (curriculum.subject != null)
                        this.subject = curriculum.subject

                if (curriculum.grade != null)
                        this.grade = curriculum.grade

                if (curriculum.capability != null)
                        this.capability = curriculum.capability

                if (curriculum.necessary != null)
                        this.necessary = curriculum.necessary

                if (curriculum.level != null)
                        this.level = curriculum.level

                if (curriculum.year != null)
                        this.year = curriculum.year

                return this

        }
}