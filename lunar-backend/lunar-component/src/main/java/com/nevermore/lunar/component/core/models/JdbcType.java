package com.nevermore.lunar.component.core.models;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nevermore.lunar.framework.enums.EnumHasIntValue;

import static com.nevermore.lunar.framework.utils.EnumUtils.enumFromValue;

/**
 * @author nevermore
 */
public enum JdbcType implements EnumHasIntValue {
    UNKNOWN(0),

    VARCHAR(1),

    TEXT(2),

    LONG_TEXT(3),

    BLOB(4),

    INT(5),

    BIGINT(6),

    TINYINT(7),

    FLOAT(8),

    DOUBLE(9),
    ;

    @EnumValue
    @JsonValue
    private final int value;

    JdbcType(int value) {
        this.value = value;
    }

    public static JdbcType fromValue(int value) {
        return enumFromValue(values(), value, UNKNOWN);
    }

    @Override
    public int getValue() {
        return value;
    }
}
