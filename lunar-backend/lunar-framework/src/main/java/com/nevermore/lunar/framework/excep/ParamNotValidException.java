package com.nevermore.lunar.framework.excep;

/**
 * 尽量在web层抛出
 *
 * @author nevermore
 */
public class ParamNotValidException extends BizException {

    public ParamNotValidException() {
    }

    public ParamNotValidException(String message) {
        super(message);
    }

    public ParamNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
