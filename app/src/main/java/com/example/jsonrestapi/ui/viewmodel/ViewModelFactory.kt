package com.example.jsonrestapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonrestapi.data.repository.PostRepositoryImpl

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(PostRepositoryImpl()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}