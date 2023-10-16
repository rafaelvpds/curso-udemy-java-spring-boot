package com.cursoudemy.persons.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cursoudemy.persons.serelization.YamlJackson2HttpMesageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // setar as origens permitidas

        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**") // todas as rotas da api
                .allowedMethods("*") // permite cors para alguns metodos ou verbos
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true); // possibilitar a autenticação
    }

    @Override

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MEDIA_TYPE_APPLICATION_YML);
    }

}
