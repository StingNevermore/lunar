package com.nevermore.lunar.component.core.models;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nevermore.lunar.framework.enums.EnumHasIntValue;

import static com.nevermore.lunar.framework.utils.EnumUtils.enumFromValue;

/**
 * @author nevermore
 */
public enum FieldType implements EnumHasIntValue {
    UNKNOWN(0),
    INT(1),
    LONG(2),
    DOUBLE(3),
    STRING(4),
    ;

    @EnumValue
    @JsonValue
    private final int value;

    FieldType(int value) {
        this.value = value;
    }

    public static FieldType fromValue(int value) {
        return enumFromValue(values(), value, UNKNOWN);
    }

    @Override
    public int getValue() {
        return value;
    }
}
