package com.nevermore.lunar.server.vo.system

/**
 * @author nevermore
 */
data class DepartmentVo(
    val id: Int,
    val deptName: String,
    val parentDept: String,
    val orderNo: Int,
    val createTime: String,
    val remark: String,
    val status: Int,
    val children: List<DepartmentVo>
)