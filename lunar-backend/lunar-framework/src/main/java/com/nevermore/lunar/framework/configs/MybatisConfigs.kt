package com.nevermore.lunar.framework.configs

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

/**
 * @author nevermore
 */
@Configuration
@MapperScan("com.nevermore.lunar.**.dao")
class MybatisConfigs

