package lanchonete.desafio.api.infra.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
             .allowedOrigins("http://localhost:3000")
                     .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

}
// .allowedOrigins("http://localhost:8080")
//         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

//http://localhost:3000/