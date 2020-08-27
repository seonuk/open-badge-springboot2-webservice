package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonManagedReference
import edu.dongguk.openBadge.domain.Role
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(length = 24, nullable = false)
    val studentID: String? = null,
    @Column(length = 100, nullable = false)
    val password: String? = null,
    @Column(length = 20, nullable = false)
    val name: String? = null,
    @Column(nullable = false)
    val major: String? = null,
    @Column(nullable = false)
    val grade: String? = null,
    @Enumerated(EnumType.STRING)
    val authority: Role,

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    val curriculumActivity: MutableList<Curriculum>? = null,

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    val noncurriculmActivity: MutableList<NonCurriculum>? = null
)
