package com.tsdreamdeveloper.app.data.mapper

import com.tsdreamdeveloper.app.data.database.model.PostDbModel
import com.tsdreamdeveloper.app.data.database.model.PostDetailDbModel
import com.tsdreamdeveloper.app.data.model.PostDetailModel
import com.tsdreamdeveloper.app.data.model.PostModel
import com.tsdreamdeveloper.app.domain.entity.PostDetailEntity
import com.tsdreamdeveloper.app.domain.entity.PostEntity
import javax.inject.Inject

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

class PostModelMapper @Inject constructor() {

    fun toEntity(from: PostModel) = PostEntity(
            likesCount = from.likesCount,
            postId = from.postId,
            previewText = from.previewText,
            timeshamp = from.timeshamp,
            title = from.title)

    fun toEntity(from: List<PostModel>) =
            from.asSequence()
                    .map {
                        PostEntity(
                                likesCount = it.likesCount,
                                postId = it.postId,
                                previewText = it.previewText,
                                timeshamp = it.timeshamp,
                                title = it.title)
                    }.toList()

    fun toEntityFromDb(from: PostDbModel) = PostEntity(
            likesCount = from.likesCount,
            postId = from.postId,
            previewText = from.previewText,
            timeshamp = from.timeshamp,
            title = from.title)

    fun toEntityFromDb(from: List<PostDbModel>) =
            from.map {
                PostEntity(
                        likesCount = it.likesCount,
                        postId = it.postId,
                        previewText = it.previewText,
                        timeshamp = it.timeshamp,
                        title = it.title)
            }

    fun toDb(from: PostEntity) = PostDbModel(
            likesCount = from.likesCount,
            postId = from.postId,
            previewText = from.previewText,
            timeshamp = from.timeshamp,
            title = from.title)

    fun toDb(from: List<PostEntity>) =
            from.map {
                PostDbModel(
                        likesCount = it.likesCount,
                        postId = it.postId,
                        previewText = it.previewText,
                        timeshamp = it.timeshamp,
                        title = it.title)
            }

    fun toDetailEntity(from: PostDetailModel) = PostDetailEntity(
            postId = from.post.postId,
            images = from.post.images,
            likesCount = from.post.likesCount,
            text = from.post.text,
            timeshamp = from.post.timeshamp,
            title = from.post.title
    )

    fun toDetailEntity(from: List<PostDetailModel>) =
            from.asSequence()
                    .map {
                        PostDetailEntity(
                                postId = it.post.postId,
                                images = it.post.images,
                                likesCount = it.post.likesCount,
                                text = it.post.text,
                                timeshamp = it.post.timeshamp,
                                title = it.post.title)
                    }.toList()

    fun toDetailEntityFromDb(from: PostDetailDbModel) = PostDetailEntity(
            postId = from.postId,
            images = from.images,
            likesCount = from.likesCount,
            text = from.text,
            timeshamp = from.timeshamp,
            title = from.title
    )

    fun toDetailEntityFromDb(from: List<PostDetailDbModel>) =
            from.map {
                PostDetailEntity(
                        postId = it.postId,
                        images = it.images,
                        likesCount = it.likesCount,
                        text = it.text,
                        timeshamp = it.timeshamp,
                        title = it.title
                )
            }

    fun toDetailDb(from: PostDetailEntity) = PostDetailDbModel(
            postId = from.postId,
            images = from.images ?: emptyList(),
            likesCount = from.likesCount,
            text = from.text,
            timeshamp = from.timeshamp,
            title = from.title
    )

    fun toDetailDb(from: List<PostDetailEntity>) =
            from.map {
                PostDetailDbModel(
                        postId = it.postId,
                        images = it.images ?: emptyList(),
                        likesCount = it.likesCount,
                        text = it.text,
                        timeshamp = it.timeshamp,
                        title = it.title
                )
            }
}