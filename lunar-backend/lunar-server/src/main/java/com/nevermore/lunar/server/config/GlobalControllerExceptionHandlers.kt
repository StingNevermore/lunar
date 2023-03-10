package com.nevermore.lunar.server.config

import com.nevermore.lunar.framework.excep.ParamNotValidException
import com.nevermore.lunar.server.vo.CommonResult
import com.nevermore.lunar.server.vo.ResultUtils.badRequestResult
import com.nevermore.lunar.server.vo.ResultUtils.internalServerErrorResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.core.annotation.AnnotationUtils.findAnnotation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

private const val COMMA = ", "

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
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun notValid(exception: MethodArgumentNotValidException): ResponseEntity<CommonResult> {
        val globalErrorMsg = if (exception.hasGlobalErrors()) {
            val errorMsg = exception.globalErrors.joinToString(COMMA) {
                "${it.objectName}: ${it.defaultMessage}"
            }
            "globalErrors: [${errorMsg}]"
        } else null

        val filedErrorMsg = if (exception.hasFieldErrors()) {
            val errorMsg =
                exception.fieldErrors.joinToString(COMMA) {
                    "${it.objectName}/${it.field}: ${it.defaultMessage}"
                }
            "filedErrors: [${errorMsg}]"
        } else null

        val msg = if (globalErrorMsg != null && filedErrorMsg != null) {
            "${globalErrorMsg}; $filedErrorMsg"
        } else if (globalErrorMsg == null) {
            "$filedErrorMsg"
        } else {
            null
        }

        return ResponseEntity.badRequest().body(badRequestResult(msg))
    }

    @ResponseBody
    @ExceptionHandler(ParamNotValidException::class)
    fun paramNotValid(exception: ParamNotValidException): ResponseEntity<CommonResult> {
        return ResponseEntity.badRequest().body(
            badRequestResult(exception.message)
        )
    }
}