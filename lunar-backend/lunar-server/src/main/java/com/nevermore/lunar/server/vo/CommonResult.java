package com.nevermore.lunar.server.vo;

import static com.nevermore.lunar.server.vo.ResultCode.BAD_REQUEST;
import static com.nevermore.lunar.server.vo.ResultCode.INTERNAL_SERVER_ERROR;
import static com.nevermore.lunar.server.vo.ResultCode.SUCCESS;

/**
 * @author nevermore
 */
public record CommonResult<T>(
        int code,
        T result,
        String message
) {

    public static CommonResult<?> successResult() {
        return from(SUCCESS);
    }

    public static CommonResult<?> badRequestResult() {
        return from(BAD_REQUEST);
    }

    public static CommonResult<?> internalServerErrorResult() {
        return from(INTERNAL_SERVER_ERROR);
    }

    public static <T> CommonResult<T> successResult(T result) {
        return from(SUCCESS, result);
    }

    public static <T> CommonResult<T> from(ResultCode code) {
        return new CommonResult<>(code.getValue(), null, code.getMessage());
    }

    public static <T> CommonResult<T> from(ResultCode code, T result) {
        return new CommonResult<>(code.getValue(), result, code.getMessage());
    }
}
