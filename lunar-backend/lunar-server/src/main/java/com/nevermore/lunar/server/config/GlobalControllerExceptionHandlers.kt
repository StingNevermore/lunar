package com.nevermore.lunar.server.config

import com.nevermore.lunar.server.vo.CommonResult
import com.nevermore.lunar.server.vo.ResultUtils.badRequestResult
import com.nevermore.lunar.server.vo.ResultUtils.internalServerErrorResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.core.annotation.AnnotationUtils.findAnnotation
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

/**
 * @author nevermore
 */
@ControllerAdvice
class GlobalControllerExceptionHandlers {
    private val log: Logger = getLogger(GlobalControllerExceptionHandlers::class.java)

    @ResponseBody
    @ExceptionHandler(Throwable::class)
    fun uncaught(request: HttpServletRequest, e: Throwable): ResponseEntity<CommonResult> {
        findAnnotation(e.javaClass, ResponseStatus::class.java)?.let { throw e }

        log.error("uri: " + request.requestURL, e)
        return ResponseEntity.internalServerError().body(internalServerErrorResult())
    }

    @ResponseBody
    @ExceptionHandler(BindException::class)
    fun notValid(exception: BindException): ResponseEntity<CommonResult> {
        return ResponseEntity.badRequest().body(
            badRequestResult(
                exception.allErrors.joinToString(",") {
                    "${it.objectName}: ${it.defaultMessage}"
                }
            )
        )
    }
}