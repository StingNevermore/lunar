package com.nevermore.lunar.framework.configs

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.util.ClassUtils
import javax.sql.DataSource

/**
 * @author nevermore
 */
@Configuration
@MapperScan("com.nevermore.lunar.**.dao")
class MybatisPlusConfigs {
    @Bean
    fun dataSource(): DataSource = DruidDataSourceWrapper().apply {
        if (hasH2Lib()) configH2() else configMysql()
    }


    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun mybatisPlusPropertiesCustomizer() = MybatisPlusPropertiesCustomizer { properties ->
        properties.globalConfig = properties.globalConfig.setBanner(false)
    }


    companion object {
        private fun DruidDataSourceWrapper.configH2() {
            driverClassName = "org.h2.Driver"
            url = "jdbc:h2:mem:lunar;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=true;mode=MySQL"
            username = "sa"
            validationQuery = "SELECT 1"
        }

        private fun DruidDataSourceWrapper.configMysql() {
            driverClassName = "com.mysql.cj.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/lunar"
            username = "root"
            password = "1234"
            initialSize = 10
            maxActive = 20
            validationQuery = "SELECT 1"
        }

        private fun hasH2Lib(): Boolean {
            return ClassUtils.isPresent("org.h2.Driver", null)
        }
    }
}