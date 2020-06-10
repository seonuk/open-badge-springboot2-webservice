package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.dtos.MemberDTO
import edu.dongguk.openBadge.domain.Role
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    @Transactional
    fun joinUser(memberDTO: MemberDTO): Member {
        val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

        val member: Member = memberDTO.copy(
            password = passwordEncoder.encode(memberDTO.password)
        ).toEntity()

        val users = memberRepository.findAll()

        for (user in users) {
            if (user.studentID == memberDTO.studentID) {
                throw Exception() // 중복 예외
            }
        }

        return memberRepository.save(member)
    }

    @Transactional
    fun modifyPassword(memberDTO: MemberDTO, id: Long, customUser: CustomUser): Member {
        val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

        val member: Member = memberDTO.copy(
            id = id,
            password = passwordEncoder.encode(memberDTO.password)
        ).toEntity()

        return memberRepository.save(member)
    }

    override fun loadUserByUsername(studentId: String?): UserDetails {
        val user: Member? = memberRepository.findByStudentID(studentId)

        val authorities: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()

        if ("admin@example.com" == studentId) {
            authorities.add(SimpleGrantedAuthority(Role.ADMIN.role))
        } else {
            authorities.add(SimpleGrantedAuthority(Role.MEMBER.role))
        }

        return user?.let {
            CustomUser(
                studentID = it.studentID,
                pw = it.password,
                authorities = authorities,
                grade = it.grade,
                name = it.name,
                major = it.major
            )
        } ?: throw Exception()
    }

    fun getUserList(): List<Member> = memberRepository.findAll()

    fun getUser(studentId: String): Member? = memberRepository.findByStudentID(studentId)
}
