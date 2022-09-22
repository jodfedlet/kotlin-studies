package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.models.User
import jodfedlet.me.kotlinstudies.basicapi.repositories.UserRepository
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.data.repository.findByIdOrNull

open class BasicController(private val usersRepository: UserRepository) {
    fun readToken(authorization: String): User {
        val token = authorization.substring(7)
        var userIdString = JWTUtils().getUserId(token)

        if (userIdString == null || userIdString.isNullOrBlank() || userIdString.isNullOrEmpty()) {
            throw IllegalArgumentException("Permission denied for this resource")
        }

        return usersRepository.findByIdOrNull(userIdString.toLong()) ?: throw IllegalArgumentException("User not found")
    }
}