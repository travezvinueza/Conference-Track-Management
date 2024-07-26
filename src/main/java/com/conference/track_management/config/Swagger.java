package com.conference.track_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(info = @Info(title = "PROBLEM TWO", description = "CONFERENCE TRACK MANAGEMENT", termsOfService = "www.programacion.com/terminos_y_condiciones", version = "1.0.0", contact = @Contact(name = "Ricardo Travez", url = "https://test-ejemplo.com", email = "travezvinueza@gmail.com"), license = @License(name = "Standard Software Use License for programador", url = "www.programacion.com/licence")))
public class Swagger implements WebMvcConfigurer {
}
