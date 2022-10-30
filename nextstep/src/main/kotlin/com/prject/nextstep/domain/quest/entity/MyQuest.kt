package com.prject.nextstep.domain.quest.entity

import com.prject.nextstep.domain.user.entity.User
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "tbl_my_quest")
class MyQuest(

    @field:EmbeddedId
    val id: MyQuestId,

    @field:MapsId("userId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: User?,

    @field:MapsId("questId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "quest_id", columnDefinition = "BINARY(16)", nullable = false)
    val quest: Quest?,

    val name: String,

    val isComplete: Boolean = false
) {

    companion object {
        fun complete(id: MyQuestId, user: User?, quest: Quest?, name: String): MyQuest {
            return MyQuest(
                id = id,
                user = user,
                quest = quest,
                name = name,
                isComplete = true
            )
        }
    }
}

@Embeddable
data class MyQuestId(
    @field:Column(nullable = false)
    val userId: UUID,

    @field:Column(nullable = false)
    val questId: UUID,

    val date: LocalDate
) : Serializable