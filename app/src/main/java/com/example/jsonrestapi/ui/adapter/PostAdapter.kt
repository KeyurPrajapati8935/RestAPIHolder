package com.example.jsonrestapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonrestapi.R
import com.example.jsonrestapi.data.model.Post

class PostAdapter(
    private val transferInterface: PostTransferInterface
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts = arrayListOf<Post>()

    fun setData(list: List<Post>) {
        posts.addAll(list)
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post: Post = posts[position]
        holder.tvId.text = post.id
        holder.tvTitle.text = post.title
        holder.itemView.setOnClickListener {
            transferInterface.onSetValues(post)
        }
    }

    override fun getItemCount(): Int = posts.size

    override fun getItemViewType(position: Int) = position
}
