package com.tsdreamdeveloper.app.domain.repository

import com.tsdreamdeveloper.app.domain.entity.PostDetailEntity
import com.tsdreamdeveloper.app.domain.entity.PostEntity
import io.reactivex.Single

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

interface PostRepo {

    fun getPostById(id: Int): Single<PostEntity>
    fun getAllPost(): Single<List<PostEntity>>
    fun getDetailedPost(id: Int): Single<PostDetailEntity>
}