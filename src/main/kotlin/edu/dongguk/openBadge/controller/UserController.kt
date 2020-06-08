package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.DTOS.MemberDTO
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/user")
class UserController(
    val memberService: MemberService
) {
    @GetMapping
    fun getUserList(): List<Member> = memberService.getUserList()

    @GetMapping("/{studentId}")
    fun getUser(
        @PathVariable
        studentId: String
    ): Member? = memberService.getUser(studentId)

    @PostMapping("/create")
    fun createUser(
        @RequestBody
        memberDTO: MemberDTO
    ): Long? = memberService.joinUser(memberDTO)

    @PutMapping("/modify/{studentId}")
    fun modifyPassword(
        @PathVariable
        studentId: Long,
        @RequestBody
        passWord: String
    ): Long? = memberService.modifyPassword(studentId, passWord)
}
