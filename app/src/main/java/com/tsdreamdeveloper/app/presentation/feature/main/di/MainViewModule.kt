package com.tsdreamdeveloper.app.presentation.feature.main.di

import android.content.Intent
import android.os.Bundle
import com.tsdreamdeveloper.app.di.PerActivity
import com.tsdreamdeveloper.app.di.PerFragment
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainActivity
import com.tsdreamdeveloper.app.presentation.feature.postdetail.di.PostDetailViewModule
import com.tsdreamdeveloper.app.presentation.feature.postdetail.view.PostDetailFragment
import com.tsdreamdeveloper.app.presentation.feature.posts.di.PostsViewModule
import com.tsdreamdeveloper.app.presentation.feature.posts.view.PostsFragment
import com.tsdreamdeveloper.app.utils.ErrorHandler
import com.tsdreamdeveloper.app.utils.HttpErrorCodeParser
import com.tsdreamdeveloper.app.utils.delegate.ResourceDelegate
import com.tsdreamdeveloper.app.utils.delegate.SnackbarDelegate
import com.tsdreamdeveloper.app.utils.ext.weak
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

@Module
abstract class MainViewModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun provideSnackbarDelegate(activity: MainActivity): SnackbarDelegate = SnackbarDelegate(activity.weak())

        @JvmStatic
        @Provides
        @PerActivity
        fun provideNavigator(activity: MainActivity): Navigator = object
            : SupportAppNavigator(activity, activity.supportFragmentManager, MainActivity.ID_CONTAINER) {
            override fun setupFragmentTransaction(
                    command: Command?,
                    currentFragment: androidx.fragment.app.Fragment?,
                    nextFragment: androidx.fragment.app.Fragment?,
                    fragmentTransaction: androidx.fragment.app.FragmentTransaction) {
            }

            override fun createStartActivityOptions(command: Command?, activityIntent: Intent?): Bundle {
                return Bundle()
            }

        }

        @JvmStatic
        @PerActivity
        @Provides
        fun provideErrorHandler(httpErrorCodeParser: HttpErrorCodeParser,
                                resourceDelegate: ResourceDelegate): ErrorHandler =
                ErrorHandler(httpErrorCodeParser, resourceDelegate)
                        .apply {

                        }
    }

    @PerFragment
    @ContributesAndroidInjector(modules = [PostsViewModule::class])
    abstract fun providePostsFragment(): PostsFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [PostDetailViewModule::class])
    abstract fun providePostDetailFragment(): PostDetailFragment
}
