package jodfedlet.me.kotlinstudies.basicapi.dtos

data class LoginResponseDTO (val name: String, val email: String, val token: String = "")