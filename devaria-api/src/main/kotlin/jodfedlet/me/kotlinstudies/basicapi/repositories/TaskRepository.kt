package jodfedlet.me.kotlinstudies.basicapi.repositories

import jodfedlet.me.kotlinstudies.basicapi.models.Task
import jodfedlet.me.kotlinstudies.basicapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t " +
            "WHERE t.user.id = :userId " +
            "   AND (:periodFrom IS NULL OR t.estimated_completion_date >= :periodFrom) " +
            "   AND (:periodTo IS NULL OR t.estimated_completion_date <= :periodTo) " +
            "   AND (:status = 0 OR (:status = 1 AND t.completion_date IS NULL)"+
            "       OR (:status = 2 AND t.completion_date IS NOT NULL))")
    fun findByUserWithFilter(userId: Long, periodFrom: LocalDate?, periodTo: LocalDate?, status: Int) : List<Task>?
}