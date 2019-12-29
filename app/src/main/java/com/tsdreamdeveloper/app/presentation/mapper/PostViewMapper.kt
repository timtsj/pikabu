package com.tsdreamdeveloper.app.presentation.mapper

import com.tsdreamdeveloper.app.domain.entity.PostDetailEntity
import com.tsdreamdeveloper.app.domain.entity.PostEntity
import com.tsdreamdeveloper.app.presentation.model.PostDetailViewModel
import com.tsdreamdeveloper.app.presentation.model.PostViewModel
import com.tsdreamdeveloper.app.utils.ext.formatDate
import javax.inject.Inject

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

class PostViewMapper @Inject constructor() {

    fun toEntity(from: PostViewModel) = PostEntity(
            postId = from.postId,
            likesCount = from.likesCount,
            previewText = from.previewText,
            timeshamp = 0,
            title = from.title
    )

    fun toEntity(from: List<PostViewModel>) =
            from.map {
                PostEntity(
                        postId = it.postId,
                        likesCount = it.likesCount,
                        previewText = it.previewText,
                        timeshamp = 0,
                        title = it.title
                )
            }

    fun toView(from: PostEntity) = PostViewModel(
            postId = from.postId,
            likesCount = from.likesCount,
            previewText = from.previewText,
            date = from.timeshamp.toLong().formatDate(),
            timeshamp = from.timeshamp * 1000.toLong(),
            title = from.title
    )

    fun toView(from: List<PostEntity>) =
            from.map {
                PostViewModel(
                        postId = it.postId,
                        likesCount = it.likesCount,
                        previewText = it.previewText,
                        date = it.timeshamp.toLong().formatDate(),
                        timeshamp = it.timeshamp * 1000.toLong(),
                        title = it.title
                )
            }

    fun toDetailEntity(from: PostDetailViewModel) = PostDetailEntity(
            postId = from.postId,
            images = from.images,
            likesCount = from.likesCount,
            text = from.text,
            timeshamp = from.date,
            title = from.title
    )

    fun toDetailEntity(from: List<PostDetailViewModel>) =
            from.map {
                PostDetailEntity(
                        postId = it.postId,
                        images = it.images,
                        likesCount = it.likesCount,
                        text = it.text,
                        timeshamp = it.date,
                        title = it.title
                )
            }

    fun toDetailView(from: PostDetailEntity) = PostDetailViewModel(
            postId = from.postId,
            images = from.images ?: emptyList(),
            likesCount = from.likesCount,
            text = from.text,
            date = from.timeshamp,
            title = from.title
    )

    fun toDetailView(from: List<PostDetailEntity>) =
            from.map {
                PostDetailViewModel(
                        postId = it.postId,
                        images = it.images ?: emptyList(),
                        likesCount = it.likesCount,
                        text = it.text,
                        date = it.timeshamp,
                        title = it.title
                )
            }
}