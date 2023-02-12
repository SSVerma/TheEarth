package com.ssverma.theearth.data.mapper

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.api.Operation
import com.ssverma.theearth.domain.Result

suspend fun <D : Operation.Data, S, E> ApolloCall<D>.asResult(
    mapData: (D) -> S,
    mapError: (error: ApiError) -> E
): Result<S, E> {
    try {
        val apiResponse = this.execute()

        if (apiResponse.hasErrors() || apiResponse.data == null) {
            return Result.Error(cause = mapError(ApiError(serverErrors = apiResponse.errors)))
        }

        return Result.Success(data = mapData(apiResponse.dataAssertNoErrors))

    } catch (e: Exception) {
        e.printStackTrace()

        return Result.Error(cause = mapError(ApiError(exception = e)))
    }
}

data class ApiError(
    val serverErrors: List<com.apollographql.apollo3.api.Error>? = null,
    val exception: Exception? = null
)
