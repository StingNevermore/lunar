package com.nevermore.lunar.framework.configs

import com.nevermore.lunar.framework.configs.mock.dao.MockTestDAO
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

/**
 * @author nevermore
 */
@SpringBootApplication
@Import(MybatisConfigs::class)
class MyBatisConfigTestsConfiguration

@MybatisTest
@TestPropertySource("classpath:application.yml")
class MyBatisConfigTests(@Autowired private val dao: MockTestDAO) {

    @Test
    fun testQueryAll() {
        val results = dao.queryAll()
        assert(results.size == 2)
        assert(results[0].state == 0)
        assert(results[1].state == 0)
    }

    @Test
    fun testQueryById() {
        val lunar = dao.queryById(1)
        assertNotNull(lunar)
        assert(lunar.id == 1L)
    }

}