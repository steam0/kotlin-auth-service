package jwt.kotlin.demo.kotlinauthservice.config

import jwt.kotlin.demo.kotlinauthservice.filters.JWTAuthenticationFilter
import jwt.kotlin.demo.kotlinauthservice.filters.JWTLoginFilter
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class WebSecurityConfig(val userDetailsService: UserDetailsService,
                        val bCryptPasswordEncoder: BCryptPasswordEncoder) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.authorizeRequests()
                ?.antMatchers("/")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/login")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/sign-up")?.permitAll()
                ?.anyRequest()?.authenticated()
            ?.and()
                // We filter the api/login requests
                ?.addFilterBefore(JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                // And filter other requests to check the presence of JWT in header
                ?.addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder)
    }
}


