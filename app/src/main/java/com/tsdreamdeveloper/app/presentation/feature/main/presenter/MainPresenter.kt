package com.tsdreamdeveloper.app.presentation.feature.main.presenter

import com.tsdreamdeveloper.app.Screens
import com.tsdreamdeveloper.app.presentation.base.BasePresenter
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
        private val router: Router
) : BasePresenter<MainView>() {

    init {
        onPosts()
    }

    private fun onPosts() {
        router.navigateTo(Screens.OnPostsScreen)
    }
}
