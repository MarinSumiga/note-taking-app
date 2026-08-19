package com.example.note_taking.core.data

import android.database.sqlite.SQLiteFullException
import com.example.note_taking.core.domain.DataError
import com.example.note_taking.core.domain.Result


suspend fun<T>safeLocalCall(
    execute: suspend ()->T
): Result<T, DataError.Local>{
    return try {
        Result.Success(execute())
    }catch(e: SQLiteFullException){
        Result.Error(DataError.Local.DISK_FULL)
    }catch(e: Exception){
        Result.Error(DataError.Local.UNKNOWN)
    }
}
