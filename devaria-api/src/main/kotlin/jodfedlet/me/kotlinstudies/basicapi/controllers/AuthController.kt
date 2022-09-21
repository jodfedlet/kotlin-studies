package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.dtos.ErrorsDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.LoginDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.LoginResponseDTO
import jodfedlet.me.kotlinstudies.basicapi.extensions.md5
import jodfedlet.me.kotlinstudies.basicapi.extensions.toHex
import jodfedlet.me.kotlinstudies.basicapi.repositories.UserRepository
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController(val usersRepository: UserRepository) {

    @PostMapping("token")
    fun login(@RequestBody loginDto: LoginDTO) : ResponseEntity<Any> {
        try {
            if (loginDto.username.isBlank() || loginDto.username.isEmpty() || loginDto.password.isEmpty() || loginDto.password.isBlank()) {
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), "Username or password are invalid"), HttpStatus.BAD_REQUEST)
            }

            val user = usersRepository.findByEmail(loginDto.username)

            if (user == null || user.password != md5(loginDto.password).toHex()){
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), "Username or password are incorrect"), HttpStatus.BAD_REQUEST)
            }

            val token = JWTUtils().createToken(user.id.toString())

            val userTest = LoginResponseDTO(user.name, user.email, token)
            return ResponseEntity(userTest, HttpStatus.OK)
        }catch (e: Exception) {
            return ResponseEntity(ErrorsDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to login, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}