package com.ssverma.theearth.data.remote

import com.apollographql.apollo3.ApolloClient

val graphqlClient by lazy {
    ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()
}