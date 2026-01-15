package vrsalex.matule.security.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vrsalex.matule.security.filter.ApiAuthFilter

@Configuration
class FilterConfig {
    @Bean
    fun apiAuthFilterRegistration(filter: ApiAuthFilter): FilterRegistrationBean<ApiAuthFilter> {
        val registration = FilterRegistrationBean(filter)
        registration.isEnabled = false
        return registration
    }
}