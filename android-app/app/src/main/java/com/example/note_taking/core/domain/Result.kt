package com.example.note_taking.core.domain

/*
* Ovo su custom generic klase koje kao resultat vracaju ili error ili response.
* Njih koristimo za mappiranje na nase responsove.
* */
sealed interface Result<out T, out E> {
    data class Success<T>(
        val value: T,
    ) : Result<T, Nothing>

    data class Error<E>(
        val error: E,
    ) : Result<Nothing, E>
}


inline fun <T, E> Result<T, E>.onSuccess(
    action: (T) -> Unit,
): Result<T, E> = apply {
    if (this is Result.Success) {
        action(value)
    }
}

inline fun <T, E> Result<T, E>.onError(
    action: (E) -> Unit,
): Result<T, E> = apply {
    if (this is Result.Error) {
        action(error)
    }
}
