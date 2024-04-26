package com.example.jsonrestapi.ui.adapter

import com.example.jsonrestapi.data.model.Post


interface PostTransferInterface {
    fun onSetValues(post: Post)
}