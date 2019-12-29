package com.tsdreamdeveloper.app.presentation.feature.posts.adapter

import android.view.View
import android.view.ViewGroup
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.model.PostViewModel
import com.tsdreamdeveloper.app.utils.ext.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsAdapter(
        private val postsAdapterListener: PostsAdapterListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private val items = mutableListOf<PostViewModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return ViewHolder(viewGroup.inflate(R.layout.item_posts))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    fun setItems(posts: List<PostViewModel>) {
        items.clear()
        items.addAll(posts)
        notifyDataSetChanged()
    }

    fun sortByDate() {
        items.sortByDescending {
            it.timeshamp
        }
        notifyDataSetChanged()
    }

    fun sortByRate() {
        items.sortByDescending {
            it.likesCount
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: PostViewModel) {
            with(containerView) {
                tvTitle.text = item.title
                tvDate.text = item.date
                tvDescription.text = item.previewText
                tvLikes.text = item.likesCount.toString()
                tvShowFull.setOnClickListener {
                    tvDescription.maxLines = Int.MAX_VALUE
                }
                containerView.setOnClickListener {
                    postsAdapterListener.onPostClick(item.postId)
                }
            }
        }
    }

    interface PostsAdapterListener {
        fun onPostClick(id: Int)
    }
}