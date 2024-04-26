package com.example.jsonrestapi.data.repository

import com.example.jsonrestapi.data.model.Post
import com.example.jsonrestapi.network.PostService

class PostRepositoryImpl : PostRepository {

    private val postService = PostService.get()

    override suspend fun getPosts(page: Int, perPage: Int): List<Post> {
        return postService.getPosts(page, perPage).map {
            Post(userId = it.userId, id = it.id, title = it.title, body = it.body)
        }
    }
}