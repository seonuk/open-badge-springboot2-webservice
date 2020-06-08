package edu.dongguk.openBadge.domain.repository

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<UserFile, Long>
