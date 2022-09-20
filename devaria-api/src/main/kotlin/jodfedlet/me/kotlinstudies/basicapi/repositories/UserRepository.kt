package jodfedlet.me.kotlinstudies.basicapi.repositories

import jodfedlet.me.kotlinstudies.basicapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: JpaRepository<User, Long> {
}