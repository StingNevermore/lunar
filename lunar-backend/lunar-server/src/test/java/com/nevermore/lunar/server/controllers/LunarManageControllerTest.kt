package com.nevermore.lunar.server.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.nevermore.lunar.component.core.models.FieldType.LONG
import com.nevermore.lunar.component.core.models.JdbcType.BIGINT
import com.nevermore.lunar.server.LunarManageController
import com.nevermore.lunar.server.vo.CommonResult.successResult
import com.nevermore.lunar.server.vo.CreateEntityVo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author nevermore
 */
@WebMvcTest(controllers = [LunarManageController::class])
class LunarManageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun testCreateEntity() {
        val vo = CreateEntityVo(
            "department", "department", "department",
            LONG, BIGINT, "", "", ""
        )
        mockMvc.perform(
            post("/api/lunar/create")
                .contentType("application/json")
                .content(vo.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(content().json(successResult().toJson()))
    }

    private fun Any.toJson() = mapper.writeValueAsString(this)

}