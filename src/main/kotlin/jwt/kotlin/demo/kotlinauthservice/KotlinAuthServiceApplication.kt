package jwt.kotlin.demo.kotlinauthservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class KotlinAuthServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinAuthServiceApplication::class.java, *args)
}

