package com.example.hikmatlar.Backend.Api.ApiService

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("api/quotes/")
    fun getQuotes(): Call<Quotes>
}