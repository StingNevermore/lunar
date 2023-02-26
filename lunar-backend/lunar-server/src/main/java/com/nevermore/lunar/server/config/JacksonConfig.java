package com.nevermore.lunar.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinFeature;
import com.fasterxml.jackson.module.kotlin.KotlinModule.Builder;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nevermore
 */
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.configure(new ObjectMapper().registerModule(
                new Builder().configure(KotlinFeature.NullToEmptyCollection, true)
                        .configure(KotlinFeature.StrictNullChecks, true)
                        .build()));
    }
}
