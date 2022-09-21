package jodfedlet.me.kotlinstudies.basicapi.dtos

class ErrorsDTO(val status: Int, val error: String? = null, val errors: List<String>? = null )