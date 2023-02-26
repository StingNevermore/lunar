package com.nevermore.lunar.framework.utils;

import com.google.common.util.concurrent.UncheckedExecutionException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nevermore
 */
@Slf4j
public class GuavaCacheUtils {

    private GuavaCacheUtils() {

    }

    public static <T, R> R safeGet(T t, ThrowableFunction<T, R> function) {
        try {
            return function.apply(t);
        } catch (UncheckedExecutionException e) {
            if (e.getCause() instanceof IllegalArgumentException iae) {
                StackTraceElement[] stackTrace = iae.getStackTrace();
                for (int i = 0; i < stackTrace.length; i++) {
                    if (stackTrace[i].getMethodName().equals("safeGet")) {
                        log.warn("unable execute function by caller: {} with arg: {}", stackTrace[i + 1], t);
                        return null;
                    }
                }
                log.warn("unable execute function to caller: {} with arg: {}", "unknown", t);
                return null;
            }
            throw e;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
