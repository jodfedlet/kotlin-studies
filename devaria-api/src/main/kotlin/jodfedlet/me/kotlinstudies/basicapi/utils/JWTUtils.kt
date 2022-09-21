package jodfedlet.me.kotlinstudies.basicapi.utils

import io.jsonwebtoken.Claims
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

    fun isTokenValid(token: String): Boolean {
        val claims = getClaimsToken(token)
       if (claims != null) {
         val userId = claims.subject
         if(!userId.isNullOrEmpty() && userId.isNotBlank()) {
             return true
         }
       }
        return false
    }

    private fun getClaimsToken(token: String) = try {
        Jwts.parser()
            .setSigningKey(JWT_SECRET_KEY.toByteArray())
            .parseClaimsJws(token)
            .body
    } catch (e: Exception) {
        null
    }

    fun getUserId(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }
}