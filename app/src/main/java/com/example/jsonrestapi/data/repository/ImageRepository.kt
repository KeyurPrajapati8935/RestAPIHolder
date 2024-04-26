package com.example.jsonrestapi.data.repository

import com.example.jsonrestapi.data.model.Post

interface PostRepository {
    suspend fun getPosts(
        page: Int,
        perPage: Int,
    ): List<Post>
}