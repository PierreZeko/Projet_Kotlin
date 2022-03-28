package com.example.chucknorrisproject

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.Single
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET


interface JokeApiService {

    @GET("jokes/random")
    fun giveMeAJoke(): Single<Joke>
}

object JokeApiServiceFactory {
    fun createService(): JokeApiService = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(JokeApiService::class.java)
}
