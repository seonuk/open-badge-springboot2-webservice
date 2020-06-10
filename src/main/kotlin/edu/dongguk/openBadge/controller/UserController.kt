package edu.dongguk.openBadge.controller

import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.dtos.MemberDTO
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.service.MemberService
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    ): Member = memberService.joinUser(memberDTO)

    @PutMapping("/modify/{id}")
    fun modifyPassword(
        @RequestBody
        memberDTO: MemberDTO,
        @PathVariable
        id: Long,
        @AuthenticationPrincipal
        customUser: CustomUser
    ): Member = memberService.modifyPassword(memberDTO, id, customUser)
}
