package edu.donnguk.davinci.dao

import edu.donnguk.davinci.dao.table.Users
import edu.donnguk.davinci.dto.User
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun getUserById(studentId: String) =
        User.find { Users.studentId eq studentId }.firstOrNull()
}