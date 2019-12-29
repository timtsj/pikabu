package com.tsdreamdeveloper.app.presentation.feature.postdetail.view

import android.os.Bundle
import android.view.View
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.BaseFragment
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainActivity
import com.tsdreamdeveloper.app.presentation.feature.postdetail.adapter.PostDetailAdapter
import com.tsdreamdeveloper.app.presentation.feature.postdetail.presenter.PostDetailPresenter
import com.tsdreamdeveloper.app.utils.delegate.SnackbarDelegate
import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import com.tsdreamdeveloper.app.utils.ext.argument
import com.tsdreamdeveloper.app.utils.ext.setVisible
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.progress_bar.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class PostDetailFragment : BaseFragment(), PostDetailView {

    companion object {
        private const val POST_ID_ARG = "POST_ID_ARG"
        fun create(idPost: Int) = PostDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(POST_ID_ARG, idPost)
            }
        }
    }

    @Inject
    lateinit var providerPresenter: Provider<PostDetailPresenter>

    @InjectPresenter
    lateinit var presenter: PostDetailPresenter

    @Inject
    lateinit var adapterPostDetail: PostDetailAdapter

    @Inject
    lateinit var snackbarDelegate: SnackbarDelegate

    @ProvidePresenter
    fun providePresenter(): PostDetailPresenter = providerPresenter.get()

    private val postId: Int by argument(POST_ID_ARG, -1)

    override fun layoutRes() = R.layout.fragment_posts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapterPostDetail
        presenter.getPost(postId)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnRetry.setOnClickListener { presenter.getPost(postId) }
    }


    override fun displayPost(items: List<String>) {
        adapterPostDetail.setData(items)
        handleError()
    }

    override fun showLoader() {
        progressBar.setVisible(true)
    }

    override fun hideLoader() {
        progressBar.setVisible(false)
    }

    override fun handleError() {
        btnRetry.setVisible(adapterPostDetail.itemCount == 0)
    }

    override fun showMessage(text: String, snackbarType: SnackbarType) {
        snackbarDelegate.showMessage(snackbarType, text)
    }
}