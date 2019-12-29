package com.tsdreamdeveloper.app.presentation.feature.posts.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.BaseFragment
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainActivity
import com.tsdreamdeveloper.app.presentation.feature.posts.adapter.PostsAdapter
import com.tsdreamdeveloper.app.presentation.feature.posts.presenter.PostsPresenter
import com.tsdreamdeveloper.app.presentation.model.PostViewModel
import com.tsdreamdeveloper.app.utils.delegate.SnackbarDelegate
import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import com.tsdreamdeveloper.app.utils.ext.setVisible
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.progress_bar.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider


class PostsFragment : BaseFragment(), PostsView, PostsAdapter.PostsAdapterListener {

    @Inject
    lateinit var providerPresenter: Provider<PostsPresenter>

    @InjectPresenter
    lateinit var presenter: PostsPresenter

    @Inject
    lateinit var postsAdapter: PostsAdapter

    @ProvidePresenter
    fun providePresenter(): PostsPresenter = providerPresenter.get()

    @Inject
    lateinit var snackbarDelegate: SnackbarDelegate

    override fun layoutRes(): Int = R.layout.fragment_posts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = postsAdapter
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
        btnRetry.setOnClickListener { presenter.getPosts() }
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun displayPosts(items: List<PostViewModel>) {
        postsAdapter.setItems(items)
        handleError()
    }

    override fun showLoader() {
        progressBar.setVisible(true)
    }

    override fun hideLoader() {
        progressBar.setVisible(false)
    }

    override fun showMessage(text: String, snackbarType: SnackbarType) {
        snackbarDelegate.showMessage(snackbarType, text)
    }

    override fun handleError() {
        btnRetry.setVisible(postsAdapter.itemCount == 0)
    }

    override fun onPostClick(id: Int) = presenter.goDetailPost(id)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_date -> {
                postsAdapter.sortByDate()
                true
            }
            R.id.action_sort_by_rate -> {
                postsAdapter.sortByRate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}