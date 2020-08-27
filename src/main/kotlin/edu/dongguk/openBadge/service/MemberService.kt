package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.dtos.MemberDTO
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import edu.dongguk.openBadge.exception.InternalServerException
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
                throw InternalServerException("duplicate User : ${user.studentID}", 101) // 중복 예외
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
        val user: Member = memberRepository.findByStudentID(studentId)
            ?: throw InternalServerException("Not Found User", 102) // not found user Exception

        val authorities: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()

        authorities.add(SimpleGrantedAuthority(user.authority.name))

        return CustomUser(
                studentID = user.studentID,
                pw = user.password,
                authorities = authorities,
                grade = user.grade,
                name = user.name,
                major = user.major
            )
    }

    fun getUserList(): List<Member> = memberRepository.findAll()

    fun getUser(studentId: String): Member? = memberRepository.findByStudentID(studentId)
}
