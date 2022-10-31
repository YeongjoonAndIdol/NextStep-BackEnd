package com.prject.nextstep.global.security.principle

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class CustomUserDetails(
    val userId: UUID
) : UserDetails {

    override fun getAuthorities() = arrayListOf(SimpleGrantedAuthority("USER"))

    override fun getUsername() = userId.toString()

    override fun getPassword() = null

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}