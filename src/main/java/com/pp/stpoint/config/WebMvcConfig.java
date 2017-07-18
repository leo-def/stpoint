// !!
// Tentativas para acessa as páginas do angular diretamente, desnecesário esta classe para o Spring Boot
package com.pp.stpoint.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", "classpath:/resources/",
		"classpath:/static/", "classpath:/public/" };
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}
