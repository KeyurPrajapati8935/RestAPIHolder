package com.example.jsonrestapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonrestapi.data.model.Post
import com.example.jsonrestapi.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel(var postRepository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _error = MutableLiveData<String?>()

    fun loadPosts(currentPage: Int, PER_PAGE: Int) {
        viewModelScope.launch {
            try {
                val newPosts =  withContext(Dispatchers.IO){
                    postRepository.getPosts(
                        currentPage,
                        PER_PAGE
                    )
                }
                _posts.postValue(newPosts)
            } catch (e: Exception) {
                _error.value = "Error fetching posts: ${e.message}"
            }
        }
    }
}
