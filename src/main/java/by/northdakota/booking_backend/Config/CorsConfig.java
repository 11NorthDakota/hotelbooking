package by.northdakota.booking_backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Разрешить все запросы на /api/*
                .allowedOrigins("http://localhost:3000") // Разрешить запросы с фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешить методы
                .allowCredentials(true); // Разрешить отправку куки
    }
}
