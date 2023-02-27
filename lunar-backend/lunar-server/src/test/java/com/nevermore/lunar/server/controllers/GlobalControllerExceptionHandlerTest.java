package com.nevermore.lunar.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevermore.lunar.server.mock.ExceptionController;
import com.nevermore.lunar.server.vo.CreateEntityVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.nevermore.lunar.component.core.models.FieldType.LONG;
import static com.nevermore.lunar.component.core.models.JdbcType.BIGINT;
import static com.nevermore.lunar.server.vo.ResultUtils.internalServerErrorResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author nevermore
 */
@WebMvcTest(controllers = ExceptionController.class, properties = "spring.main.banner-mode=off")
public class GlobalControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testInternalServerError() throws Exception {
        mockMvc.perform(get("/api/exception/internalServerError"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(mapper.writeValueAsString(internalServerErrorResult())));
    }

    @Test
    public void testNotFound() throws Exception {
        mockMvc.perform(get("/whatever"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBadRequest() throws Exception {
        CreateEntityVo vo = new CreateEntityVo("department", "department", "",
                LONG, BIGINT, "", "", "");
        mockMvc.perform(post("/api/exception/badRequest")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(vo)))
                .andExpect(status().isBadRequest());
    }
}
