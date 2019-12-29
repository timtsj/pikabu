package com.tsdreamdeveloper.app.presentation.feature.postdetail.view

import com.tsdreamdeveloper.app.presentation.base.CanHandleError
import com.tsdreamdeveloper.app.presentation.base.CanShowLoader
import moxy.MvpView

interface PostDetailView : MvpView, CanShowLoader, CanHandleError {
    fun displayPost(items: List<String>)
}