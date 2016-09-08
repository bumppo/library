package com.dev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.dev.service", "com.dev.dto.converter", "com.dev.validation"})
public class CoreConfig {

}
