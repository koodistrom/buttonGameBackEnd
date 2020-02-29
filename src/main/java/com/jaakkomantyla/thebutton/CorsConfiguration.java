package com.jaakkomantyla.thebutton;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cors configuration to allow fetch requests.
 */
@Configuration
public class CorsConfiguration
{
    /**
     * Cors configurer web mvc configurer.
     *
     * @return web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowCredentials(true)
                            .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }
}
