package com.tsdreamdeveloper.app.presentation.feature.posts.presenter

import com.tsdreamdeveloper.app.Screens
import com.tsdreamdeveloper.app.domain.usecase.post.GetAllPostUseCase
import com.tsdreamdeveloper.app.presentation.base.BasePresenter
import com.tsdreamdeveloper.app.presentation.feature.posts.view.PostsView
import com.tsdreamdeveloper.app.presentation.mapper.PostViewMapper
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PostsPresenter @Inject constructor(
        private val router: Router,
        private val postViewMapper: PostViewMapper,
        private val postsUseCase: GetAllPostUseCase
) : BasePresenter<PostsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getPosts()
    }

    fun getPosts() {
        postsUseCase.execute(GetAllPostUseCase.Param())
                .map {
                    postViewMapper.toView(it)
                }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSubscribe { viewState.showLoader() }
                .doAfterTerminate { viewState.hideLoader() }
                .subscribe({
                    viewState.displayPosts(it)
                }, {
                    errorHandler.handleError(it)
                    viewState.handleError()
                })
                .also { disposables.add(it) }
    }

    fun goDetailPost(postId: Int) {
        router.navigateTo(Screens.OnPostDetailScreen(postId))
    }
}