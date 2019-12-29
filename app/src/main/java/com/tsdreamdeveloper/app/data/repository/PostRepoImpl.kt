package com.tsdreamdeveloper.app.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.tsdreamdeveloper.app.data.database.dao.PostDao
import com.tsdreamdeveloper.app.data.mapper.PostModelMapper
import com.tsdreamdeveloper.app.data.network.PikabuApi
import com.tsdreamdeveloper.app.domain.entity.PostDetailEntity
import com.tsdreamdeveloper.app.domain.entity.PostEntity
import com.tsdreamdeveloper.app.domain.repository.PostRepo
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

class PostRepoImpl @Inject constructor(
        private val pikabuApi: PikabuApi,
        private val postModelMapper: PostModelMapper,
        private val postDao: PostDao,
        private val context: Context
) : PostRepo {

    override fun getPostById(id: Int): Single<PostEntity> =
            Single.fromCallable { postDao.getPost(id) }
                    .map { postModelMapper.toEntityFromDb(it) }

    override fun getDetailedPost(id: Int): Single<PostDetailEntity> =
            pikabuApi.getPostDetailed(id)
                    .map {
                        return@map postModelMapper.toDetailEntity(it)
                    }

    override fun getAllPost(): Single<List<PostEntity>> {
        return isInternetOn(context)
                .flatMap { isNetworkAvailable ->
                    if (isNetworkAvailable) {
                        return@flatMap postFromNetwork()
                    } else {
                        return@flatMap postFromDb()
                    }
                }
    }

    private fun isInternetOn(context: Context): Single<Boolean> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return Single.just(activeNetworkInfo != null && activeNetworkInfo.isConnected)
    }

    private fun postFromNetwork() = pikabuApi.getPosts()
            .map { postModelMapper.toEntity(it.posts) }
            .doOnSuccess { postDao.insertOrUpdate(postModelMapper.toDb(it)) }

    private fun postFromDb() = Single.fromCallable {
        postDao.getAllPost().map { postModelMapper.toEntityFromDb(it) }
    }
}