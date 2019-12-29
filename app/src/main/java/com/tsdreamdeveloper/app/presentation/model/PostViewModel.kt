package com.tsdreamdeveloper.app.presentation.model

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

data class PostViewModel(
        val postId: Int,
        val likesCount: Int,
        val previewText: String,
        val date: String,
        val timeshamp: Long,
        val title: String
)

data class PostDetailViewModel(
        val postId: Int,
        val images: List<String>,
        val likesCount: Int,
        val text: String,
        val date: Int,
        val title: String
)