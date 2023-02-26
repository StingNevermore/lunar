package com.nevermore.lunar.component;

import com.nevermore.lunar.framework.configs.MybatisPlusConfigs;
import org.camunda.bpm.spring.boot.starter.CamundaBpmAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

/**
 * @author nevermore
 */
@SpringBootApplication(exclude = {CamundaBpmAutoConfiguration.class})
@Import(MybatisPlusConfigs.class)
@TestPropertySource(value = "classpath:application.yml")
public class MyBatisConfigTestsConfiguration {
}
