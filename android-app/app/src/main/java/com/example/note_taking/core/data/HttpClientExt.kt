package com.example.note_taking.core.data

import com.example.note_taking.core.domain.DataError
import com.example.note_taking.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

/**
 * Utility klasa za mapiranje pojedinih response errora s mreze na greske
 * koje mi mozemo prikazati na nasem UI
 *
 * Isto tako imamo i Utility klasu Za hvatanje tih errora prilikom poziva prema networku ili APIju
 * */

suspend inline fun <reified T> safeNetworkCall(
    execute: ()-> HttpResponse
): Result<T, DataError.Remote>{
    val response = try {
        execute()
    }catch (e: SocketTimeoutException){
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    }catch (e: UnresolvedAddressException){
        return Result.Error(DataError.Remote.UNKNOWN)
    }catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote>{
    return when(response.status.value){
        in 200..299 ->{
            try {
                Result.Success(response.body<T>())
            }catch (e: NoTransformationFoundException){
                Result.Error(DataError.Remote.SERIALIZATION_ERROR)
            }
        }
        408 ->Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}