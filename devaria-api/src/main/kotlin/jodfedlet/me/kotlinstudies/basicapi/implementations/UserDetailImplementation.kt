package jodfedlet.me.kotlinstudies.basicapi.implementations

import jodfedlet.me.kotlinstudies.basicapi.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails as UserDetails1

class UserDetailImplementation(private val user: User): UserDetails1 {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}