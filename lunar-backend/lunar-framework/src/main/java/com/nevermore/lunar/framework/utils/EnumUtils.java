package com.nevermore.lunar.framework.utils;

import com.nevermore.lunar.framework.enums.EnumHasIntValue;

import java.util.Arrays;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author nevermore
 */
public class EnumUtils {

    private EnumUtils() {

    }

    public static <T extends EnumHasIntValue> T enumFromValue(T[] values, int value, T defaultValue) {
        return Arrays.stream(values).collect(toMap(T::getValue, identity()))
                .getOrDefault(value, defaultValue);
    }
}
