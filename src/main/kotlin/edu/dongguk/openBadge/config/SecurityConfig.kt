package edu.dongguk.openBadge.config

import edu.dongguk.openBadge.Filter.JwtAuthenticationFilter
import edu.dongguk.openBadge.Filter.JwtAuthorizationFilter
import edu.dongguk.openBadge.domain.repository.MemberRepository
import edu.dongguk.openBadge.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val memberRepository: MemberRepository,
    val memberService: MemberService
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/css/**", "/js/**", "/img/**", "/api/admin/user/create")
    }

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(JwtAuthenticationFilter(authenticationManager(), memberRepository))
            .addFilter(JwtAuthorizationFilter(authenticationManager(), memberRepository))
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/admin/user/create").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers("api/admin/*").hasRole("ADMIN")
            .antMatchers("api/").hasAnyRole("ADMIN", "MEMBER")
            .anyRequest().authenticated()
            .and()
            .cors()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(memberService)

        return daoAuthenticationProvider
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.addAllowedMethod("*")
        configuration.addAllowedHeader("*")
        configuration.setAllowCredentials(true)
        configuration.setMaxAge(3600L)
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
