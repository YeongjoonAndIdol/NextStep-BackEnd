package com.prject.nextstep.domain.user.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @field:Column(unique = true)
    val email: String?,

    @field:Column(nullable = false)
    val name: String,

    @field:Column(unique = true)
    val accountId: String?,

    val password: String?

) {

   companion object {
       fun oAuthUser(email: String, name: String) : User{
           return User(
               id = null,
               email = email,
               name = name,
               accountId = null,
               password = null
           )
       }

       fun signUpUser(name: String, accountId: String?, password: String?) : User{
           return User(
               id = null,
               email = null,
               name = name,
               accountId = accountId,
               password = password
           )
       }
   }

}