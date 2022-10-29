package com.prject.nextstep.domain.user.entity.repository

import com.prject.nextstep.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun existsByEmail(email: String): Boolean
    fun findByAccountId(email: String): User?
}