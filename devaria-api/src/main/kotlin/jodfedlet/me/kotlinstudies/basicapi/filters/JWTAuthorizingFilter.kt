package jodfedlet.me.kotlinstudies.basicapi.filters

import jodfedlet.me.kotlinstudies.basicapi.AUTHORIZATION
import jodfedlet.me.kotlinstudies.basicapi.BEARER
import jodfedlet.me.kotlinstudies.basicapi.implementations.UserDetailImplementation
import jodfedlet.me.kotlinstudies.basicapi.models.User
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizingFilter(authenticationManager: AuthenticationManager, private val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManager) {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(AUTHORIZATION)

        if (authorization != null && authorization.startsWith(BEARER)) {
            val authorized = getAuthentication(authorization)
            SecurityContextHolder.getContext().authentication = authorized
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(authorization: String): UsernamePasswordAuthenticationToken {
        val token = authorization.substring(7)
        if (jwtUtils.isTokenValid(token)) {
            val idString = jwtUtils.getUserId(token)

            if (!idString.isNullOrEmpty() && idString.isNotBlank()) {
                val user = User(idString.toLong(), "User test","admin@admin.com", "admin")
                val userImplem = UserDetailImplementation(user)
                return UsernamePasswordAuthenticationToken(user, null, userImplem.authorities)
            }
        }
        throw UsernameNotFoundException("Invalid token")
    }
}