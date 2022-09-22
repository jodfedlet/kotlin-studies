package jodfedlet.me.kotlinstudies.basicapi.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var estimated_completion_date: LocalDate = LocalDate.MIN,
    var completion_date: LocalDate? = null,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    val user: User? = null
)
