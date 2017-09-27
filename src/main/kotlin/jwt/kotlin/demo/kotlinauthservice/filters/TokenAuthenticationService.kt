package jwt.kotlin.demo.kotlinauthservice.filters

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationService {
    companion object {
        val EXPIRATIONTIME = 864_000_000
        val SECRET = "ThisIsKotlin"
        val TOKEN_PREFIX = "Bearer"
        val HEADER_STRING = "Authorization"

        fun addAuthentication(response : HttpServletResponse?,
                              username : String,
                              claims : Map<String, Any>) {

            val JWT = Jwts
                    .builder()
                    .setSubject(username)
                    .setClaims(claims)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()

            response?.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT)
        }

        fun getAuthentication(request : HttpServletRequest) : Authentication? {
            val token = request.getHeader(HEADER_STRING)

            if (token != null) {
                val user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();


                return if (user != null) UsernamePasswordAuthenticationToken(user, null, emptyList()) else null
            }

            return null
        }
    }
}