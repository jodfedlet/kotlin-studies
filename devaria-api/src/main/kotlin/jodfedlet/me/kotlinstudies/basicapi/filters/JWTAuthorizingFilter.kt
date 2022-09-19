package jodfedlet.me.kotlinstudies.basicapi.filters

import jodfedlet.me.kotlinstudies.basicapi.AUTHORIZATION
import jodfedlet.me.kotlinstudies.basicapi.BEARER
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizingFilter(authenticationManager: AuthenticationManager, val jwtUtils: JWTUtils): BasicAuthenticationFilter(authenticationManager) {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(AUTHORIZATION)

        if (authorization != null && authorization.startsWith(BEARER)) {
            val authorized = getAuthentication(authorization)
        }
    }

    private fun getAuthentication(authorization: String): Any {

    }
}