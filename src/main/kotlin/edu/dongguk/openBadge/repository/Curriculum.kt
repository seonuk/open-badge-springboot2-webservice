package edu.dongguk.openBadge.repository

import javax.persistence.*

@Entity
class Curriculum(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        @Column(length = 500, nullable = false)
        val subject: String,
        @Column(nullable = false)
        val grade: String,
        @Column(nullable = false)
        val capability: Int,
        @Column(nullable = false)
        val necessary: String,
        @Column(nullable = false)
        val level: String,
        @Column(nullable = false)
        val year: Int
)