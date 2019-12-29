package com.tsdreamdeveloper.app.di.module

import com.tsdreamdeveloper.app.data.repository.PostRepoImpl
import com.tsdreamdeveloper.app.di.PerApplication
import com.tsdreamdeveloper.app.domain.repository.PostRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    @PerApplication
    fun providePostRepoImpl(postRepoImpl: PostRepoImpl): PostRepo
}