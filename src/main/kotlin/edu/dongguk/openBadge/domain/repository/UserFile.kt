package edu.dongguk.openBadge.domain.repository

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity(name ="file")
class UserFile(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val fileName: String? = null,
        val fileDownloadURI: String? = null,
        val fileType: String? = null,
        val fileSize: Long? = null,

        @ManyToOne
        @JoinColumn(name = "noncurriculum_id")
        @JsonBackReference
        var nonCurriculum: NonCurriculum? = null
)