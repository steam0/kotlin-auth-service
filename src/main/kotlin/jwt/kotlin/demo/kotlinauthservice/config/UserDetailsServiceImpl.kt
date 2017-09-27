package jwt.kotlin.demo.kotlinauthservice.config

import jwt.kotlin.demo.kotlinauthservice.domain.repositories.ApplicationUserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val applicationUserRepository: ApplicationUserRepository) : UserDetailsService {
    override fun loadUserByUsername(nullableUsername: String?): UserDetails {
        val username = nullableUsername ?: throw UsernameNotFoundException(nullableUsername)

        val applicationUser = applicationUserRepository.findUserByUsername(nullableUsername) ?: throw UsernameNotFoundException(username)

        return User(applicationUser.username, applicationUser.password, emptyList())
    }
}
