package jodfedlet.me.kotlinstudies.basicapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude =[SecurityAutoConfiguration::class])
class BasicApiApplication

fun main(args: Array<String>) {
	runApplication<BasicApiApplication>(*args)
}
