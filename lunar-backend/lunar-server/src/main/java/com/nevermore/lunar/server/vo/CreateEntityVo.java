package com.nevermore.lunar.server.vo;

import com.nevermore.lunar.component.core.models.FieldType;
import com.nevermore.lunar.component.core.models.JdbcType;
import com.nevermore.lunar.server.validation.validators.EntityExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author nevermore
 */
public record CreateEntityVo(
        @NotBlank
        @EntityExists
        String entityName,
        @NotBlank
        String entityDesc,
        @NotBlank
        String fieldName,
        @NotNull
        FieldType fieldType,
        @NotNull
        JdbcType jdbcType,
        @NotNull
        String defaultValue,
        @NotNull
        String placeHolder,
        @NotNull
        String schemaDesc
) {
}
