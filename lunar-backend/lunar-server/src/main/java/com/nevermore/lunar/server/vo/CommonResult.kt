@file:Suppress("unused")

package com.nevermore.lunar.server.vo

import com.nevermore.lunar.server.vo.ResultCode.BAD_REQUEST
import com.nevermore.lunar.server.vo.ResultCode.INTERNAL_SERVER_ERROR
import com.nevermore.lunar.server.vo.ResultCode.SUCCESS

/**
 * @author nevermore
 */
sealed class LunarApiResult(
    val code: Int,
    val message: String
)

class CommonResult(code: Int, message: String) : LunarApiResult(code, message)

class DataResult<T>(code: Int, val data: T?, message: String) : LunarApiResult(code, message)

object ResultUtils {

    @JvmStatic
    @JvmOverloads
    fun successResult(message: String? = null) = from(SUCCESS, message)

    @JvmStatic
    @JvmOverloads
    fun badRequestResult(message: String? = null) = from(BAD_REQUEST, message)

    @JvmStatic
    @JvmOverloads
    fun internalServerErrorResult(message: String? = null) = from(INTERNAL_SERVER_ERROR, message)

    @JvmStatic
    fun <T> successResultWithData(data: T?): DataResult<T> = DataResult(SUCCESS.value, data, SUCCESS.message)

    @JvmStatic
    @JvmOverloads
    fun from(code: ResultCode, message: String? = null) = CommonResult(code.value, message ?: code.message)
}
