package com.example.hikmatlar.Backend

import com.example.hikmatlar.Backend.Api.Hashtag

data class QuotesItem(
    val author: String,
    val created_at: String,
    val hashtags: List<Hashtag>,
    val id: Int,
    val text: String,
    val translation: String
)