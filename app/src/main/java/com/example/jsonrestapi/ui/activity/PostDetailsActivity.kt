package com.example.jsonrestapi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.TextView
import com.example.jsonrestapi.R
import com.example.jsonrestapi.data.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView
    private lateinit var tvPostDetailsError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        initView()

        val postJson = intent.getStringExtra("post_details")

        if (postJson != null) {
            val post: Post? = Gson().fromJson(postJson, object : TypeToken<Post>() {}.type)
            post?.let {
                tvTitle.text = it.title
                tvBody.text = it.body
            }
        } else {
            tvPostDetailsError.visibility = VISIBLE
            tvPostDetailsError.text =
                getString(R.string.unable_to_fetch_the_post_details_please_try_it_again)
        }
    }

    private fun initView() {
        tvTitle = findViewById(R.id.tvPostTitle)
        tvBody = findViewById(R.id.tvPostBody)
        tvPostDetailsError = findViewById(R.id.tvPostDetailsError)
    }
}