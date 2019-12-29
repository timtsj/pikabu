package com.tsdreamdeveloper.app.data.network

import com.tsdreamdeveloper.app.data.model.ApiResponse
import com.tsdreamdeveloper.app.data.model.PostDetailModel
import com.tsdreamdeveloper.app.data.model.PostModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

interface PikabuApi {

    @GET("files/api201910/posts.json")
    fun getPosts(): Single<ApiResponse<List<PostModel>>>

    @GET("files/api201910/{id}.json")
    fun getPostDetailed(@Path("id") id: Int): Single<PostDetailModel>
}