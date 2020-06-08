package edu.dongguk.openBadge.domain.repository

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUser(
    val studentID: String?,
    val pw: String?,
    val name: String?,
    val major: String?,
    val grade: String?,
    val authorities: ArrayList<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getUsername(): String? = studentID
    override fun getPassword(): String? = pw
    override fun isEnabled(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true

//    fun toEntity(): Member = Member(
//            studentID = this.studentID,
//            password = this.pw
//    name: String?,
//    major: String?,
//    grade: String?
//    )
//
}
