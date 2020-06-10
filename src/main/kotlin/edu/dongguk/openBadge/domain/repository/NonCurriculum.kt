package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "non_curriculum")
data class NonCurriculum(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val capability: String,
    @Column(length = 500, nullable = false)
    val activityName: String,
    @Column(nullable = false)
    val necessary: String,
    @Column(nullable = false)
    val level: String,
    @Column(nullable = false)
    val division: String,
    @Column(nullable = false)
    val format: String,
    @Column(nullable = false)
    val field: String,
    @Column(nullable = false)
    val content: String,
    @Column(nullable = false)
    val start_day: String,
    @Column(nullable = false)
    val end_day: String,
    @Column(nullable = false)
    val total_time: Int,
    @Column(nullable = false)
    val participants: Int,
    @Column(nullable = false)
    val program_level: Int,
    @Column(nullable = false)
    val self_evaluation: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val user: Member? = null,

    @OneToMany(mappedBy = "nonCurriculum")
    @JsonManagedReference
    val files: MutableList<UserFile>? = null

)
