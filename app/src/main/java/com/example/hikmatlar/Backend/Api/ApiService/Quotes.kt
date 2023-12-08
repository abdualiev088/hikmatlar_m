package com.example.hikmatlar.Backend.Api.ApiService

data class Quotes(
    val quotes: List<Quote>,
    val total_quotes: Int
)