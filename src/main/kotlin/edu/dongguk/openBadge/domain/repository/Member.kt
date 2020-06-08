package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonManagedReference
import edu.dongguk.openBadge.DTOS.CurriculumDTO
import javax.persistence.*

@Entity (name = "user")
class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(length = 24 , nullable = false)
        val studentID: String? = null,
        @Column(length = 100, nullable = false)
        var password: String? = null,
        @Column(length = 20, nullable =false)
        val name: String? = null,
        @Column(nullable = false)
        val major: String? = null,
        @Column(nullable = false)
        val grade: String? = null,

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        val curriculumActivity: MutableList<Curriculum>? = null,

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        val noncurriculmActivity: MutableList<NonCurriculum>? = null
) {
        fun updatePassword(password: String?) {
                this.password = password
        }
}