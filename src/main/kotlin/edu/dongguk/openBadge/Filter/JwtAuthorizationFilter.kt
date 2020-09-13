package edu.dongguk.openBadge.Filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import edu.dongguk.openBadge.JwtProperties
import edu.dongguk.openBadge.domain.repository.Member
import edu.dongguk.openBadge.domain.repository.MemberRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(
    authenticationManage: AuthenticationManager,
    private val memberRepository: MemberRepository,
    private val JwtProperties: JwtProperties
) : BasicAuthenticationFilter(authenticationManage) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val header: String = request.getHeader(JwtProperties.HEADER_STRING)

        if (!header.startsWith(JwtProperties.TOKEN_PREFIX!!)) {
            chain.doFilter(request, response)
            return
        }

        val authentication: Authentication = getUsernamePasswordAuthentication(request)
        SecurityContextHolder.getContext().authentication = authentication

        chain.doFilter(request, response)
    }

    private fun getUsernamePasswordAuthentication(request: HttpServletRequest): Authentication {
        val studentId: String? = request.getHeader(JwtProperties.HEADER_STRING)?.let {
            JWT.require(HMAC512(JwtProperties.SECRET?.toByteArray()))
                .build()
                .verify(it.replace(JwtProperties.TOKEN_PREFIX!!, ""))
                .subject
        }

        studentId?.let {
            val user: Member = memberRepository.findByStudentID(it) ?: throw Exception("User is not exist")
            val authorities: ArrayList<GrantedAuthority> = ArrayList()

            authorities.add(SimpleGrantedAuthority(user.authority.name))

            return UsernamePasswordAuthenticationToken(studentId, null, authorities)
        } ?: throw Exception("studentId is null!")
    }
}
