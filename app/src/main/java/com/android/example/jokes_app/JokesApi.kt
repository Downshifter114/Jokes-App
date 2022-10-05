package com.android.example.jokes_app

import retrofit2.http.GET

interface JokesApi {
    @GET("joke/Any?type=single")
    suspend fun getSingleJoke() : SingleJoke?

    @GET("joke/Any?type=twopart")
    suspend fun getTwoPartJoke() : TwoPartJoke?
}