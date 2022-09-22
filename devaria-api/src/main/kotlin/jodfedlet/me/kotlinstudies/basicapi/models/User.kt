package jodfedlet.me.kotlinstudies.basicapi.models

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name ="users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    var password: String = "",
    var role: String = "manager",

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val tasks: List<Task> = emptyList()
    )