package com.nevermore.lunar.server.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author nevermore
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(0, "ok"),
    BAD_REQUEST(400, "bad request"),
    INTERNAL_SERVER_ERROR(500, "internal sever error");

    private final int value;
    private final String message;

}
