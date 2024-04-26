package com.example.jsonrestapi.network

import com.example.jsonrestapi.data.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("posts")
    suspend fun getPosts(
        @Query("page") pageNo: Int,
        @Query("per_page") perPage: Int,
    ): List<Post>

    companion object {
        fun get(): PostService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PostService::class.java)
        }

        var BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}