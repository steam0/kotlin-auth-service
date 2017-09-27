package com.example.jwt.kotlin.demo.kotlinauthservice.domain.repository

import com.example.jwt.kotlin.demo.kotlinauthservice.domain.model.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository


interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
    fun findUserByUsername(username: String): ApplicationUser
}