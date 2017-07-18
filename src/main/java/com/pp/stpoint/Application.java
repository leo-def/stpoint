package com.pp.stpoint;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@EnableRedisHttpSession
@SpringBootApplication
public class Application extends SpringBootServletInitializer {


    private static final Class<Application> applicationClass = Application.class;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}

/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Environment env;

    @Value("${spring.datasource.url}")
     private String datasourceUrl;
    
@PostConstruct
    public void initApplication() {
        for (String profile : env.getActiveProfiles()) {
            log.warn("ActiveProfile " + profile);
        }
    }

*/