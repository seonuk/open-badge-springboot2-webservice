package edu.donnguk.davinci.controller

import edu.donnguk.davinci.dto.User
import edu.donnguk.davinci.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{studentId}")
    fun getUser(@PathVariable("studentId") studentId: String,
                @RequestParam("major") major: String?,
                @RequestParam("class") studentClass: String?): User? {
        return userService.getUserWithFilter(studentId, major, studentClass)
    }
}