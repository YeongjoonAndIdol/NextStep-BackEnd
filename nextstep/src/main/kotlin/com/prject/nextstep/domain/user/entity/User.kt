package com.prject.nextstep.domain.user.entity

import com.prject.nextstep.domain.user.dto.RankingResponseDto
import com.prject.nextstep.global.common.BaseEntity
import org.springframework.data.jpa.repository.Query
import java.util.UUID
import javax.persistence.*

@Entity
@NamedNativeQuery(
    name = "queryRanking",
    query ="select u.id, u.name, rank(), u.level over (order by u.level desc) from User u",
    resultSetMapping = "rankingResponseDto"
)
@SqlResultSetMapping(
    name = "rankingResponseDto",
    classes = (
            arrayOf(
                ConstructorResult(
                    targetClass = RankingResponseDto::class,
                    columns = (
                            arrayOf(
                                ColumnResult(name = "id", type = UUID::class),
                                ColumnResult(name = "name", type = String::class),
                                ColumnResult(name = "ranking", type = Int::class),
                                ColumnResult(name = "level", type = Int::class)
                            )
                    )
                )
            )
    )
) // TODO native 쿼리 대체방안 찾기
@Table(name = "tbl_user")
class User(

    override val id: UUID = UUID(0, 0),

    @field:Column(unique = true)
    val email: String?,

    @field:Column(nullable = false)
    val name: String,

    @field:Column(unique = true)
    val accountId: String?,

    val password: String?,

    val level: Int,

    val exp: Int,

    val walkCount: Int

) : BaseEntity() {

   companion object {
       fun oAuthUser(email: String, name: String) : User{
           return User(
               email = email,
               name = name,
               accountId = null,
               password = null,
               level = 1,
               exp = 0,
               walkCount = 0
           )
       }

       fun signUpUser(name: String, accountId: String?, password: String?) : User{
           return User(
               email = null,
               name = name,
               accountId = accountId,
               password = password,
               level = 1,
               exp = 0,
               walkCount = 0
           )
       }
   }

}