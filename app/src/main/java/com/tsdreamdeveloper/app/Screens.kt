package com.tsdreamdeveloper.app

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainActivity
import com.tsdreamdeveloper.app.presentation.feature.postdetail.view.PostDetailFragment
import com.tsdreamdeveloper.app.presentation.feature.posts.view.PostsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object OnMainFlow : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
                Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
    }

    object OnPostsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = PostsFragment()
    }

    data class OnPostDetailScreen(val idPost: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment = PostDetailFragment.create(idPost)
    }
}