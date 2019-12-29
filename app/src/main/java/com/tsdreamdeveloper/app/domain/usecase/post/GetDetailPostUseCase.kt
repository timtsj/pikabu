package com.tsdreamdeveloper.app.domain.usecase.post

import com.tsdreamdeveloper.app.domain.entity.PostDetailEntity
import com.tsdreamdeveloper.app.domain.repository.PostRepo
import com.tsdreamdeveloper.app.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

class GetDetailPostUseCase @Inject constructor(
        private val postRepo: PostRepo
) : SingleUseCase<GetDetailPostUseCase.Param, PostDetailEntity>() {

    override fun build(parameters: Param): Single<PostDetailEntity> =
            postRepo.getDetailedPost(parameters.idPost)

    data class Param(val idPost: Int)
}