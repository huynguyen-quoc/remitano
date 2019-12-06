package com.huynguyen.videosharing.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.huynguyen.videosharing"})
@EnableJpaRepositories(basePackages = {"com.huynguyen.videosharing"})
@EnableJpaAuditing(dateTimeProviderRef = "currentTimeStampProvider")
public class JPAAutoconfigure {

}
