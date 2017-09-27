package jwt.kotlin.demo.kotlinauthservice.controllers

import jwt.kotlin.demo.kotlinauthservice.domain.exceptions.UsernameAlreadyInUseException
import jwt.kotlin.demo.kotlinauthservice.domain.exceptions.UsernameNotProvidedException
import jwt.kotlin.demo.kotlinauthservice.domain.models.ApplicationUser
import jwt.kotlin.demo.kotlinauthservice.domain.repositories.ApplicationUserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/")
class WebRestController(val applicationUserRepository: ApplicationUserRepository,
                        val bCryptPasswordEncoder: BCryptPasswordEncoder) {


    val log = LoggerFactory.getLogger(WebRestController::class.java)

    @GetMapping
    fun getUser() {
        val applicationUser = ApplicationUser("Henrik", "1234password")
        print("Application user: " + applicationUser)

        val savedUser = applicationUserRepository.save(applicationUser)
        print("Saved user: " + savedUser)

        val foundUser = applicationUserRepository.findUserByUsername("Henrik")
        print("Found user: " + foundUser)
    }

    @GetMapping("/userinfo")
    fun getUserInfo(): ApplicationUser? {
        val authentication = SecurityContextHolder.getContext().authentication;

        log.info("Returning userinfo for user: {} ", authentication.principal.toString())

        return applicationUserRepository.findUserByUsername(authentication.principal.toString());
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user : ApplicationUser) {
        val username = user.username ?: throw UsernameNotProvidedException("Missing username")
        val existingUser = applicationUserRepository.findUserByUsername(username)

        if (existingUser != null) {
            throw UsernameAlreadyInUseException("Username is already in use.")
        }

        user.password = bCryptPasswordEncoder.encode(user.password)

        val savedUser = applicationUserRepository.save(user)

        log.info("Saving new user={} with username={} and password={}", savedUser.id, savedUser.username, savedUser.password)
    }

}