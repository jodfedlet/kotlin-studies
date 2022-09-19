package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.dtos.ErrorsDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.LoginDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.LoginResponseDTO
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController {

    private val LOGIN_TEST = "admin@admin.com"
    private val PASSWORD_TEST = "admin"

    @PostMapping("token")
    fun login(@RequestBody loginDto: LoginDTO) : ResponseEntity<Any> {
        try {
            if (loginDto.username.isBlank() || loginDto.username.isEmpty() || loginDto.password.isEmpty() || loginDto.password.isBlank()
                || loginDto.password != PASSWORD_TEST || loginDto.username != LOGIN_TEST
            ) {
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), "Username or password are invalid"), HttpStatus.BAD_REQUEST)
            }

            val userId = 1
            val token = JWTUtils().createToken(userId.toString())

            val userTest = LoginResponseDTO("User Test", LOGIN_TEST, token)
            return ResponseEntity(userTest, HttpStatus.OK)
        }catch (e: Exception) {
            return ResponseEntity(ErrorsDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to login, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}