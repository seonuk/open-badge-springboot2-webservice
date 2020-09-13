package edu.dongguk.openBadge.Filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.fasterxml.jackson.databind.ObjectMapper
import edu.dongguk.openBadge.JwtProperties
import edu.dongguk.openBadge.domain.repository.CustomUser
import edu.dongguk.openBadge.dtos.LoginViewModel
import edu.dongguk.openBadge.exception.InternalServerException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Date
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    authenticationManage: AuthenticationManager,
    private val JwtProperties: JwtProperties
) : UsernamePasswordAuthenticationFilter() {

    init {
        authenticationManager = authenticationManage
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val credential: LoginViewModel =
            ObjectMapper().readValue(request?.inputStream, LoginViewModel::class.java)
            ?: throw InternalServerException("Authentication Error", 101)

        val authenticationToken = UsernamePasswordAuthenticationToken(
            credential.userName,
            credential.password
        )

        val result = authenticationManager.authenticate(authenticationToken)
        setDetails(request, authenticationToken)
        SecurityContextHolder.getContext().authentication = result

        return result
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val principal = authResult?.principal as CustomUser

        val token: String = JWT.create()
            .withSubject(principal.studentID)
            .withExpiresAt(Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME!!))
            .sign(HMAC512(JwtProperties.SECRET?.toByteArray()))

        response?.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token)
    }
}
