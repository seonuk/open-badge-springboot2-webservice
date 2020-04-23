package edu.donnguk.davinci.service

import edu.donnguk.davinci.dao.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserWithFilter(studentId: String,
                          studentClass: String?,
                          major: String?) =
        userRepository.getUserById(studentId)

}