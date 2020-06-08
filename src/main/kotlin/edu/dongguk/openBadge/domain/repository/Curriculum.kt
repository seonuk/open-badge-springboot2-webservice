package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import edu.dongguk.openBadge.DTOS.CurriculumDTO
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
    var year: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    var user: Member? = null

) {
    fun mappingUser(user: Member?) {
        this.user = user
    }

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
