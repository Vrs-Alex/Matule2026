package vrsalex.matule.controller.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import vrsalex.matule.api.endpoints.ServerEndpoints

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/image/**")
            .addResourceLocations("file:uploads/images/")
            .setCachePeriod(0)
            .resourceChain(true)
    }
}