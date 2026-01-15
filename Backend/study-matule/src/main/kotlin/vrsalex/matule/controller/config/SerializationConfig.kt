package vrsalex.matule.controller.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SerializationConfig : WebMvcConfigurer {


    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val serializationConverter = KotlinSerializationJsonHttpMessageConverter(
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        )
        converters.add(0, serializationConverter)
    }
}