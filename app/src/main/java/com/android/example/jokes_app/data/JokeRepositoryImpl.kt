package com.android.example.jokes_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeRepositoryImpl() : JokeRepository {
    override fun getInstance(): Retrofit {
        val baseUrl = "https://v2.jokeapi.dev"
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}