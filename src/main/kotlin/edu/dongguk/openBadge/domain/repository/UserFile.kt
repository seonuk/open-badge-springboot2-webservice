package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "file")
class UserFile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val fileName: String? = null,
    val fileDownloadURI: String? = null,
    val fileSize: Long? = null,

    @ManyToOne
    @JoinColumn(name = "noncurriculum_id")
    @JsonBackReference
    val nonCurriculum: NonCurriculum? = null
)
