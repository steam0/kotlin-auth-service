package jwt.kotlin.demo.kotlinauthservice.domain.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ApplicationUser(var username: String? = null, var password: String? = null , @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = 0L)
