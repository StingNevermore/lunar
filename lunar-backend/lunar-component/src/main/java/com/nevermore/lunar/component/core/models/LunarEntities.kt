package com.nevermore.lunar.component.core.models

/**
 * @author nevermore
 */
data class LunarEntity(
    val entityName: String,
    val createTime: Long,
    val creatorId: Long,
    val desc: String
)

data class LunarEntitySchema(
    val entityName: String,
    val fieldName: String,
    val fieldType: FieldType,
    val jdbcType: JdbcType,
    val defaultValue: String,
    val placeHolder: String,
    val createTime: Long,
    val creatorId: Long,
    val desc: String
)