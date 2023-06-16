package com.example.hikmatlar.Backend.Api.ApiService

import com.example.hikmatlar.Backend.Quotes
import com.example.hikmatlar.Backend.QuotesItem
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("quotes")
    fun getQuotes(): Call<List<QuotesItem>>
}