package com.azeem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

    @Bean
    public WebMvcConfigurer corsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(GET, POST, PUT, DELETE)
                        .allowedHeaders("*")
                        .allowedOrigins("http://51.20.87.123:3000","http://51.20.87.123:8082","http://192.168.0.20:9090","http://150.129.239.228:9090","http://localhost:9090","http://localhost:8082","http://192.168.0.20:8082","http://localhost:3000","http://192.168.0.20:3000","http://150.129.239.228:8082")
                        .allowCredentials(true);
            }
        };
    }
}
