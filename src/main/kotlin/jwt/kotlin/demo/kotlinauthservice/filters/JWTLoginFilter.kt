package jwt.kotlin.demo.kotlinauthservice.filters

import com.fasterxml.jackson.databind.ObjectMapper
import jwt.kotlin.demo.kotlinauthservice.domain.models.ApplicationUser
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter : AbstractAuthenticationProcessingFilter {

    constructor(url : String, authenticationManager : AuthenticationManager) : super(AntPathRequestMatcher(url)) {
        setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val creds = ObjectMapper().readValue(request?.inputStream, ApplicationUser::class.java)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(creds.username, creds.password, emptyList()))
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val user = authResult?.principal as User

        var claims : Map<String, Any> = mapOf("User Role" to "ADMIN", "sub" to user.username)

        TokenAuthenticationService.addAuthentication(response, authResult.name, claims)
    }
}