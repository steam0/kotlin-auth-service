package jwt.kotlin.demo.kotlinauthservice.domain.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UsernameNotProvidedException(message: String, vararg objects: Any) : RuntimeException(String.format(message, *objects))