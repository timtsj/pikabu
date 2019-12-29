package com.tsdreamdeveloper.app.presentation.feature.posts.view

import com.tsdreamdeveloper.app.presentation.base.CanHandleError
import com.tsdreamdeveloper.app.presentation.base.CanShowLoader
import com.tsdreamdeveloper.app.presentation.model.PostViewModel
import moxy.MvpView

interface PostsView : MvpView, CanShowLoader, CanHandleError {

    fun displayPosts(items: List<PostViewModel>)

}