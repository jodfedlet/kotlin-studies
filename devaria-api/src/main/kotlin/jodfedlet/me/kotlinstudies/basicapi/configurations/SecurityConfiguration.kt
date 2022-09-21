package jodfedlet.me.kotlinstudies.basicapi.configurations

import jodfedlet.me.kotlinstudies.basicapi.filters.JWTAuthorizingFilter
import jodfedlet.me.kotlinstudies.basicapi.repositories.UserRepository
import jodfedlet.me.kotlinstudies.basicapi.utils.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var  jwtUtils: JWTUtils

    @Autowired
    private lateinit var usersRepository: UserRepository

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST,"/api/auth/token").permitAll()
            .antMatchers(HttpMethod.POST,"/api/users").permitAll()
            .anyRequest().authenticated()

        http.cors().configurationSource(configurationCors())
        http.addFilter(JWTAuthorizingFilter(authenticationManager(), jwtUtils, usersRepository))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun configurationCors() : CorsConfigurationSource?{
        val configuration = CorsConfiguration()

        configuration.allowedOrigins = mutableListOf("*")
        configuration.allowedMethods = mutableListOf("*")
        configuration.allowedHeaders = mutableListOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}