package com.nevermore.lunar.component.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nevermore
 */
@Data
@Component
@ConfigurationProperties("lunar")
public class LunarConfiguration {
    
}
