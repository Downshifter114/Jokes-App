package com.android.example.jokes_app

import retrofit2.Retrofit

interface JokeRepository {
    fun getInstance() : Retrofit
}