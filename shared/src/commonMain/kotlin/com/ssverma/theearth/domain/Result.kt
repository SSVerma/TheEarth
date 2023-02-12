package com.ssverma.theearth.domain

sealed interface Result<out S, out E> {
    data class Success<S>(
        val data: S
    ) : Result<S, Nothing>

    data class Error<E>(
        val cause: E
    ) : Result<Nothing, E>
}