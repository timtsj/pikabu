package com.tsdreamdeveloper.app.presentation.feature.postdetail.presenter

import com.tsdreamdeveloper.app.domain.usecase.post.GetDetailPostUseCase
import com.tsdreamdeveloper.app.presentation.base.BasePresenter
import com.tsdreamdeveloper.app.presentation.feature.postdetail.view.PostDetailView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class PostDetailPresenter @Inject constructor(
        private val detailPostUseCase: GetDetailPostUseCase
) : BasePresenter<PostDetailView>() {

    fun getPost(postId: Int) {
        detailPostUseCase.execute(GetDetailPostUseCase.Param(postId))
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSubscribe { viewState.showLoader() }
                .doFinally { viewState.hideLoader() }
                .map {
                    val list = mutableListOf(it.title, it.text)
                    it.images?.let { images ->
                        list.addAll(images)
                    }
                    list
                }
                .subscribe({
                    viewState.displayPost(it)
                }, {
                    errorHandler.handleError(it)
                    viewState.handleError()
                })
                .also { disposables.add(it) }
    }
}