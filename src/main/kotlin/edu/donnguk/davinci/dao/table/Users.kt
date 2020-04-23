package edu.donnguk.davinci.dao.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val studentId = varchar("studentId", 50)
    val name = varchar("name", 50)
    val studentClass = varchar("class", 50)
    val major = varchar("major", 50)

    override val primaryKey = PrimaryKey(id)
}