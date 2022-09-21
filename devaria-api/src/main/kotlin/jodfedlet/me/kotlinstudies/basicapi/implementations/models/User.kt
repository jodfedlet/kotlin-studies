package jodfedlet.me.kotlinstudies.basicapi.implementations.models

import javax.persistence.*

@Entity
@Table(name ="users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    var password: String = "",
    var role: String = "manager"
    )