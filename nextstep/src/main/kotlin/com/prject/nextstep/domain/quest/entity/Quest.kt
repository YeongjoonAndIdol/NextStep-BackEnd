package com.prject.nextstep.domain.quest.entity

import com.prject.nextstep.global.common.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tbl_quest")
class Quest(

    override val id: UUID = UUID(0, 0),

    val name: String,

    val period: String,

    val time: String,

    val content: String,

    val schoolType: String

) : BaseEntity()