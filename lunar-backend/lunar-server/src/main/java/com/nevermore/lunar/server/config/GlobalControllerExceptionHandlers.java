package com.nevermore.lunar.server.config;

import com.nevermore.lunar.server.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static com.nevermore.lunar.server.vo.CommonResult.internalServerErrorResult;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @author nevermore
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandlers {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommonResult<?>> uncaught(HttpServletRequest request, Throwable e) throws Throwable {
        if (findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        log.error("uri: " + request.getRequestURL(), e);
        return ResponseEntity.internalServerError().body(internalServerErrorResult());
    }
}
