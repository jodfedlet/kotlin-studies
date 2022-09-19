package jodfedlet.me.kotlinstudies.basicapi.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jodfedlet.me.kotlinstudies.basicapi.JWT_SECRET_KEY
import org.springframework.stereotype.Component

@Component
class JWTUtils {

    fun createToken(userId: String) : String {
        return Jwts.builder()
            .setSubject(userId)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY.toByteArray())
            .compact()
    }
}