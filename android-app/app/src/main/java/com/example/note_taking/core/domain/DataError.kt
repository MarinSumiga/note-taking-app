package com.example.note_taking.core.domain

sealed interface DataError{
    enum class Remote: DataError {
        NO_CONNECTION,
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        SERVER_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN
    }
    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}
