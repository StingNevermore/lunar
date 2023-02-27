package com.nevermore.lunar.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevermore.lunar.server.vo.ResultUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.nevermore.lunar.server.vo.ResultCode.NOT_FOUND;

/**
 * @author nevermore
 */
@Controller
@RequestMapping("${service.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    private final ObjectMapper mapper;

    public CustomErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties, ObjectMapper mapper) {
        super(errorAttributes, serverProperties.getError());
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }

        if (status == HttpStatus.NOT_FOUND) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = mapper.convertValue(ResultUtils.from(NOT_FOUND), Map.class);
            return new ResponseEntity<>(map, status);
        }

        return super.error(request);
    }
}
