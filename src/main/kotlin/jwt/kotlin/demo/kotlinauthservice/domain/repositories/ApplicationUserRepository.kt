package jwt.kotlin.demo.kotlinauthservice.domain.repositories

import jwt.kotlin.demo.kotlinauthservice.domain.models.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository


interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
    fun findUserByUsername(username: String): ApplicationUser?
}