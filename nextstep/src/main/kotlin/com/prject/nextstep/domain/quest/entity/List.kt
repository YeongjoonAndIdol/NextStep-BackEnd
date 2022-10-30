package com.prject.nextstep.domain.quest.entity

import com.prject.nextstep.domain.user.entity.User
import com.prject.nextstep.global.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "tbl_list")
class List(

    val type: String,

    val title: String,

    val content: String,

    val exp: Int,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    val quest: Quest?

): BaseEntity() {

}