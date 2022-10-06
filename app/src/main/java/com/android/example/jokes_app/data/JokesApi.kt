package com.android.example.jokes_app.data

import com.android.example.jokes_app.model.TwoPartJoke
import com.android.example.jokes_app.model.SingleJoke
import com.android.example.jokes_app.utils.Globals.ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Url

interface JokesApi {
    @GET
    suspend fun getSingleJoke(@Url url: String) : SingleJoke?

    @GET
    suspend fun getTwoPartJoke(@Url url: String) : TwoPartJoke?
}