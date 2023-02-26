package com.nevermore.lunar.framework.utils;

/**
 * @author nevermore
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;
}
