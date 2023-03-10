package com.nevermore.lunar.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevermore.lunar.server.mock.ExceptionController;
import com.nevermore.lunar.server.utils.LocaleMessageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.test.web.servlet.MockMvc;

import static com.nevermore.lunar.server.vo.ResultUtils.badRequestResult;
import static com.nevermore.lunar.server.vo.ResultUtils.internalServerErrorResult;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author nevermore
 */
@WebMvcTest(controllers = ExceptionController.class,
        properties = "spring.main.banner-mode=off",
        includeFilters = {@Filter(classes = LocaleMessageHelper.class, type = ASSIGNABLE_TYPE)}
)
public class GlobalControllerExceptionHandlerTest {

    public static final String CODE = "lunar.validation.constraints.EntityExists.message";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LocaleMessageHelper messageHelper;

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
    public void testParamNotValid() throws Exception {
        mockMvc.perform(get("/api/exception/paramNotValid"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(mapper.writeValueAsString(
                        badRequestResult(messageHelper.getMessage(CODE)))));
    }
}
