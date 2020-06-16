package edu.dongguk.openBadge.config

import edu.dongguk.openBadge.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val memberService: MemberService
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/css/**", "/js/**", "/img/**")
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.headers()?.frameOptions()?.disable()
            ?.and()
            ?.authorizeRequests()
            ?.antMatchers("/api/admin/user")
            ?.hasRole("MEMBER")
            ?.antMatchers("/api/**")
            ?.hasRole("MEMBER")
//            ?.antMatchers("/**")
//            ?.permitAll()
            ?.and()
            ?.formLogin()
            ?.defaultSuccessUrl("/user/login/result")
            ?.permitAll()
            ?.and()
            ?.logout()
            ?.logoutSuccessUrl("/user/logout/result")
            ?.invalidateHttpSession(true)
            ?.and()
            ?.exceptionHandling()
            ?.accessDeniedPage("/user/denied")
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(memberService)?.passwordEncoder(passwordEncoder())
    }
}
