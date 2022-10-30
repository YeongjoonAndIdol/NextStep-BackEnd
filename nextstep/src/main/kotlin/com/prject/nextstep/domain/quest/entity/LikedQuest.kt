package com.prject.nextstep.domain.quest.entity

import com.prject.nextstep.domain.user.entity.User
import java.io.Serializable
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "tbl_liked_quest")
class LikedQuest(

    @field:EmbeddedId
    val id: LikedQuestId,

    @field:MapsId("userId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: User?,

    @field:MapsId("questId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "quest_id", columnDefinition = "BINARY(16)", nullable = false)
    val quest: Quest?,

    val name: String,
)

@Embeddable
data class LikedQuestId(
    @field:Column(nullable = false)
    val userId: UUID,

    @field:Column(nullable = false)
    val questId: UUID
) : Serializable