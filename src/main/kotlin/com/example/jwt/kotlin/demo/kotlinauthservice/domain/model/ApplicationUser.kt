package com.example.jwt.kotlin.demo.kotlinauthservice.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ApplicationUser(var username: String, var password: String, @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0)

