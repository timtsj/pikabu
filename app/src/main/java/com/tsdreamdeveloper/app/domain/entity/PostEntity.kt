package com.tsdreamdeveloper.app.domain.entity

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

data class PostEntity(
        val postId: Int,
        val likesCount: Int,
        val previewText: String,
        val timeshamp: Int,
        val title: String
)

data class PostDetailEntity(
        val postId: Int,
        val images: List<String>?,
        val likesCount: Int,
        val text: String,
        val timeshamp: Int,
        val title: String
)