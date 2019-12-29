package com.tsdreamdeveloper.app.presentation.feature.posts.di

import com.tsdreamdeveloper.app.di.PerFragment
import com.tsdreamdeveloper.app.presentation.feature.posts.adapter.PostsAdapter
import com.tsdreamdeveloper.app.presentation.feature.posts.view.PostsFragment
import dagger.Module
import dagger.Provides

@Module
abstract class PostsViewModule {

    @Module
    companion object {

        @PerFragment
        @JvmStatic
        @Provides
        fun providePostsAdapter(fragment: PostsFragment) = PostsAdapter(fragment)
    }
}