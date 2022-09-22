package jodfedlet.me.kotlinstudies.basicapi.controllers

import jodfedlet.me.kotlinstudies.basicapi.dtos.*
import jodfedlet.me.kotlinstudies.basicapi.models.Task
import jodfedlet.me.kotlinstudies.basicapi.repositories.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("api/tasks")
class TaskController(usersRepository: UserRepository, val tasksRepository: TaskRepository) : BasicController(usersRepository) {

    @GetMapping
    fun findAll(
        @RequestHeader("Authorization") authorization : String,
        @RequestParam periodFrom: Optional<String>,
        @RequestParam periodTo: Optional<String>,
        @RequestParam status: Optional<Int>
    ): ResponseEntity<Any> {
        return try {
            val user = readToken(authorization)

            val periodFromToDate = if (periodFrom.isPresent && periodFrom.get().isNotEmpty()) {
                LocalDate.parse(periodFrom.get())
            }else {
                null
            }

            val periodToToDate = if (periodTo.isPresent && periodTo.get().isNotEmpty()) {
                LocalDate.parse(periodTo.get())
            }else {
                null
            }

            val statusInt = if (periodTo.isPresent) status.get() else 0

            val tasks = tasksRepository.findByUserWithFilter(user.id, periodFromToDate, periodToToDate, statusInt)
            ResponseEntity(tasks, HttpStatus.OK)
        }catch (e: Exception){
            ResponseEntity(
                ErrorsDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.message), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun create(@RequestBody req: Task, @RequestHeader("Authorization") authorization : String): ResponseEntity<Any> {
        return try {

            val user = readToken(authorization)
            val errors = mutableListOf<String>()
            if(req == null) {
                errors.add("Task not found")
            } else {
                if (req.name.isNullOrBlank() || req.name.isNullOrEmpty() || req.name.length < 4){
                    errors.add("Invalid name")
                }

                if (req.estimated_completion_date.isBefore(LocalDate.now())) {
                    errors.add("Estimated date must be before today")
                }
            }

            if (errors.size > 0){
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), errors = errors), HttpStatus.BAD_REQUEST)
            }

            val task = Task(name = req.name,  estimated_completion_date = req.estimated_completion_date, user = user)
             tasksRepository.save(task)
             ResponseEntity(SuccessDTO("Task created successfully"), HttpStatus.CREATED)
        }catch (e: Exception) {
             ResponseEntity(
                ErrorsDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to create task, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody updateModel: Task,
        @RequestHeader("Authorization") authorization : String
    ) : ResponseEntity<Any>{

        return try {

            val user = readToken(authorization)
            val task = tasksRepository.findByIdOrNull(id)
            if (task == null || user == null || task.user?.id != user.id){
                return ResponseEntity(
                    ErrorsDTO(
                        HttpStatus.NOT_FOUND.value(),
                        "Task not found"), HttpStatus.NOT_FOUND)
            }

            val errors = mutableListOf<String>()
            if (updateModel == null) {
                errors.add("Please, specify the data you want to update")
            } else {
                if (!updateModel.name.isNullOrEmpty() && !updateModel.name.isNullOrBlank() && updateModel.name.length < 4){
                    errors.add("Invalid name")
                }


                if (updateModel.estimated_completion_date.isBefore(LocalDate.now())) {
                    errors.add("Estimated date must be before today")
                }

                if (updateModel.completion_date != null && updateModel.completion_date == LocalDate.MIN) {
                    errors.add("Invalid completion date")
                }
            }

            if (errors.size > 0){
                return ResponseEntity(ErrorsDTO(HttpStatus.BAD_REQUEST.value(), errors = errors), HttpStatus.BAD_REQUEST)
            }

            if (!updateModel.name.isNullOrEmpty() && !updateModel.name.isNullOrBlank()){
                task.name = updateModel.name
            }


            if (!updateModel.estimated_completion_date.isBefore(LocalDate.now())) {
               task.estimated_completion_date = updateModel.estimated_completion_date
            }

            if (updateModel.completion_date != null && updateModel.completion_date != LocalDate.MIN) {
                task.completion_date = updateModel.completion_date
            }
            tasksRepository.save(task)
            ResponseEntity(SuccessDTO("Task update successfully"), HttpStatus.OK)
        }catch (e: Exception) {
            ResponseEntity(
                ErrorsDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to update task, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, @RequestHeader("Authorization") authorization : String) : ResponseEntity<Any>{

        return try {

            val user = readToken(authorization)

            val task = tasksRepository.findByIdOrNull(id)
            if (task == null || task.user?.id != user.id){
                return ResponseEntity(
                    ErrorsDTO(
                        HttpStatus.NOT_FOUND.value(),
                        "Task not found"), HttpStatus.NOT_FOUND)
            }

            tasksRepository.deleteById(id)
            ResponseEntity(SuccessDTO("Task deleted successfully"), HttpStatus.OK)
        }catch (e: Exception) {
            ResponseEntity(
                ErrorsDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to delete task, try later"), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
}