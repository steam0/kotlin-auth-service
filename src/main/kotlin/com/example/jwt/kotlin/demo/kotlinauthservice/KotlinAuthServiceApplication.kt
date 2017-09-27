package com.example.jwt.kotlin.demo.kotlinauthservice

import com.example.jwt.kotlin.demo.kotlinauthservice.domain.model.ApplicationUser
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinAuthServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinAuthServiceApplication::class.java, *args)

    val applicationUser = ApplicationUser("Henrik", "1234password")

    print("Application user: " + applicationUser)
//        val savedUser = applicationUserRepository.save(applicationUser)

//        val foundUser = applicationUserRepository.findUserByUsername("Henrik")

//        print("Saved user: " + savedUser)

//        print("Found user: " + foundUser)

}

