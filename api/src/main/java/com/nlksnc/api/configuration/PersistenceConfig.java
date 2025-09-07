package com.nlksnc.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingTimeDateProvider")
public class PersistenceConfig {
    @Bean
    public DateTimeProvider auditingTimeDateProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }
}
