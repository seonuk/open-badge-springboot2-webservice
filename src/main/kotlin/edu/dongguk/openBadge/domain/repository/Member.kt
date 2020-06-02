package edu.dongguk.openBadge.domain.repository

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
        @Column(nullable = false)
        val major: String? = null,
        @Column(nullable = false)
        val grade: String? = null
){
        fun updatePassword(password: String?) {
                this.password = password
        }
}