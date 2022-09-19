package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.dtos.ErrorsDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.LoginDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController {

    @PostMapping
    fun login(@RequestBody loginDto: LoginDTO) : ResponseEntity<Any> {
        try {
            throw RuntimeException("Testing exception")
        }catch (e: Exception) {
            return ResponseEntity(ErrorsDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to login, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}