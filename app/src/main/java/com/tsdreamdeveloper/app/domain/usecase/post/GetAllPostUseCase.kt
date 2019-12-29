package com.tsdreamdeveloper.app.domain.usecase.post

import com.tsdreamdeveloper.app.domain.entity.PostEntity
import com.tsdreamdeveloper.app.domain.repository.PostRepo
import com.tsdreamdeveloper.app.domain.usecase.base.SingleUseCase
import javax.inject.Inject

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

class GetAllPostUseCase @Inject constructor(
        private val postRepo: PostRepo

) : SingleUseCase<GetAllPostUseCase.Param, List<PostEntity>>() {

    override fun build(parameters: Param) = postRepo.getAllPost()

    class Param
}