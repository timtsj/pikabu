//package com.tsdreamdeveloper.app.domain.interactor
//
//import com.tsdreamdeveloper.app.domain.entity.PostEntity
//import com.tsdreamdeveloper.app.domain.repository.PostRepo
//import io.reactivex.Flowable
//import javax.inject.Inject
//
//class PostInteractor @Inject constructor(
//        private val postRepo: PostRepo
//) {
//
//    fun getAllPost(): Flowable<List<PostEntity>> {
//        return postRepo.getAllPost()
//    }
//}