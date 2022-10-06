package com.android.example.jokes_app.data

import retrofit2.Retrofit

interface JokeRepository {
    fun getInstance() : Retrofit
}