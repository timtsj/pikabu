package com.tsdreamdeveloper.app.presentation.feature.postdetail.di

import com.tsdreamdeveloper.app.di.PerFragment
import com.tsdreamdeveloper.app.presentation.feature.postdetail.adapter.PostDetailAdapter
import dagger.Module
import dagger.Provides

@Module
abstract class PostDetailViewModule {

    @Module
    companion object {

        @PerFragment
        @JvmStatic
        @Provides
        fun providePostsAdapter() = PostDetailAdapter()
    }
}
