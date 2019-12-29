package com.tsdreamdeveloper.app.presentation.feature.postdetail.adapter

import android.view.View
import android.view.ViewGroup
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.utils.ext.inflate
import com.tsdreamdeveloper.app.utils.ext.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_text.view.*
import kotlinx.android.synthetic.main.item_title.view.*
import javax.inject.Inject


class PostDetailAdapter @Inject constructor() : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder =
            when (viewType) {
                ItemStyle.TITLE.value -> ViewHolder(container.inflate(R.layout.item_title))
                ItemStyle.TEXT.value -> ViewHolder(container.inflate(R.layout.item_text))
                else -> ViewHolder(container.inflate(R.layout.item_image))
            }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> ItemStyle.TITLE.value
                1 -> ItemStyle.TEXT.value
                else -> ItemStyle.IMAGE.value
            }

    fun setData(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(text: String) {
            with(containerView) {
                when (getItemViewType(adapterPosition)) {
                    ItemStyle.TITLE.value -> {
                        tvTitle.text = text
                    }
                    ItemStyle.TEXT.value -> {
                        wvText.loadData(text, "text/html; charset=utf-8", null)
                    }
                    ItemStyle.IMAGE.value -> {
                        ivPost.load(text)
                    }
                }
            }
        }
    }

    enum class ItemStyle(val value: Int) {
        TITLE(0),
        TEXT(1),
        IMAGE(2)
    }
}