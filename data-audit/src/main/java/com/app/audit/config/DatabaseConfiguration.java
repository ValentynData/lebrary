package com.app.audit.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.app.audit.repository"})
@EntityScan(value = "com/app/audit/entities")
public class DatabaseConfiguration {
}
