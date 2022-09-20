package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.dtos.ErrorsDTO
import jodfedlet.me.kotlinstudies.basicapi.dtos.SuccessDTO
import jodfedlet.me.kotlinstudies.basicapi.models.User
import jodfedlet.me.kotlinstudies.basicapi.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController(val usersRepository: UserRepository) {

    @GetMapping
    fun findAll() = User(1, "User Test", "admin@admin.com","")

    @PostMapping
    fun create(@RequestBody userDto: User): ResponseEntity<Any> {
        return try {
            val errors = mutableListOf<String>()

            if (userDto == null){
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), "Invalid parameters"), HttpStatus.BAD_REQUEST)
            }

            if (userDto.name.isNullOrBlank() || userDto.name.isNullOrEmpty() || userDto.name.length < 3) {
                errors.add("Invalid user name")
            }

            if (userDto.email.isNullOrBlank() || userDto.email.isNullOrEmpty() || userDto.name.length < 5) {
                errors.add("Invalid user email")
            }

            if (userDto.password.isNullOrBlank() || userDto.password.isNullOrEmpty() || userDto.password.length < 4) {
                errors.add("Invalid user password")
            }

            if (errors.size > 0){
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), errors = errors), HttpStatus.BAD_REQUEST)
            }

            usersRepository.save(userDto)

            ResponseEntity(SuccessDTO("User created successfully"), HttpStatus.OK)
        }catch (e: Exception){
            ResponseEntity(
                ErrorsDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to create user, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}