package com.prject.nextstep.domain.user.entity.repository

import com.prject.nextstep.domain.user.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String>{
}