package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.models.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController {

    @GetMapping
    fun findAll() = User(1, "User Test", "admin@admin.com","")
}