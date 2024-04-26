package com.example.jsonrestapi.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonrestapi.R
import com.example.jsonrestapi.data.model.Post
import com.example.jsonrestapi.ui.adapter.PostAdapter
import com.example.jsonrestapi.ui.adapter.PostTransferInterface
import com.example.jsonrestapi.ui.viewmodel.PostViewModel
import com.example.jsonrestapi.ui.viewmodel.ViewModelFactory
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), PostTransferInterface {

    private lateinit var viewModel: PostViewModel
    private lateinit var adapter: PostAdapter

    private var currentPage = 1
    private val PER_PAGE = 20

    private lateinit var layoutManager: LinearLayoutManager
    private var isLoading = false

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        setUpAdapter()

        setViewModelFactory()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }
            }
        })

        viewModelObservers()

        loadData()
    }

    private fun initView() {
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.rvPost)
    }

    private fun setUpAdapter() {
        adapter = PostAdapter(this)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun setViewModelFactory() {
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
    }

    private fun viewModelObservers() {
        viewModel.posts.observe(this) { list ->
            adapter.setData(list)
            isLoading = false
            progressBar.visibility = View.GONE
        }
    }

    private fun loadMoreItems() {
        currentPage++
        loadData()
    }

    private fun loadData() {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        viewModel.loadPosts(currentPage, PER_PAGE)
    }

    override fun onSetValues(post: Post) {
        val postGson = Gson().toJson(post)
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra("post_details", postGson)
        startActivity(intent)
    }
}





