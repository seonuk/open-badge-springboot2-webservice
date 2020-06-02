package edu.dongguk.openBadge.service

import edu.dongguk.openBadge.DTOS.MemberDTO
import edu.dongguk.openBadge.domain.Role
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
class MemberService(
        val memberRepository: MemberRepository
) : UserDetailsService{

    fun todo(){
        TODO("패스워드 변경")
    }
    @Transactional
    fun joinUser(memberDTO : MemberDTO) : Long? {
        val passwordEncoder : PasswordEncoder = BCryptPasswordEncoder()

        memberDTO.password = passwordEncoder.encode(memberDTO.password)

        //중복 방지

        return memberRepository.save(memberDTO.toEntity()).id
    }

    @Transactional
    fun modifyPassword(studentId: Long, passWord: String): Long? {
        val member: Member = memberRepository.getOne(studentId)
        val passwordEncoder : PasswordEncoder = BCryptPasswordEncoder()

        member.updatePassword(passwordEncoder.encode(passWord))

        return memberRepository.save(member).id
    }
    override fun loadUserByUsername(studentId: String?): UserDetails {
        val userEntityWrapper: Optional<Member> = memberRepository.findByStudentID(studentId)
        val user: Member = userEntityWrapper.get()
        val authorities: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()

        if ("admin@example.com" == studentId) {
            authorities.add(SimpleGrantedAuthority(Role.ADMIN.role))
        } else {
            authorities.add(SimpleGrantedAuthority(Role.MEMBER.role))

        }

        return User(user.studentID,user.password, authorities)
    }

    fun getUserList(): List<Member> = memberRepository.findAll()

    fun getUser(studentId: String): Member? {

        val member: Optional<Member> = memberRepository.findByStudentID(studentId)

        if (member.isEmpty) {
            println("null?")
        }
        return member.orElse(null)
    }


}
