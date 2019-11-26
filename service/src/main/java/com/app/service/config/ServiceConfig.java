package com.app.service.config;

import com.app.audit.config.DatabaseConfiguration;
import com.app.security.jwt.config.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfiguration.class, SecurityConfig.class})
@ComponentScan(basePackages = "com.app.service")
public class ServiceConfig {
}
