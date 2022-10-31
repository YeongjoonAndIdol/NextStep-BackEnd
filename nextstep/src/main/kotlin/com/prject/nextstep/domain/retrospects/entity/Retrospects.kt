package com.prject.nextstep.domain.retrospects.entity

import com.prject.nextstep.domain.user.entity.User
import com.prject.nextstep.global.common.BaseEntity
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "tbl_retrospects",
    uniqueConstraints = [
        UniqueConstraint(columnNames = arrayOf("date", "user_id"))
    ]
)
class Retrospects(

    override val id: UUID = UUID(0, 0),

    val content: String,

    val date: LocalDate,

    @field:OneToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id", columnDefinition = "BINARY(16)")
    val user: User?

): BaseEntity()