package edu.donnguk.davinci.dto

import edu.donnguk.davinci.dao.table.Users
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    val studentId by Users.studentId
    val name by Users.name
    val studentClass by Users.studentClass
    val major by Users.major
}