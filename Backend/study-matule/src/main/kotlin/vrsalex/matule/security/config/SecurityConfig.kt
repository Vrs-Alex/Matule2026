package vrsalex.matule.security.config


import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.security.filter.ApiAuthFilter
import vrsalex.matule.security.filter.DelegatingExceptionHandlerFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val apiAuthFilter: ApiAuthFilter,
    private val delegatingExceptionHandlerFilter: DelegatingExceptionHandlerFilter
) {

    @Bean
    @Order(0)
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .securityMatcher("${ServerEndpoints.API.API_PREFIX}/**")
            .csrf { it.disable() }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authRequest ->
                authRequest
                    .requestMatchers(*ServerEndpoints.API.PUBLIC_ENDPOINTS.toTypedArray()).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(delegatingExceptionHandlerFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(apiAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

            .exceptionHandling { configurer ->
                configurer.authenticationEntryPoint { _, response, _ ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                }
            }

        return http.build()
    }


}