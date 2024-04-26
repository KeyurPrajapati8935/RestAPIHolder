package com.example.jsonrestapi.data.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val userId: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)