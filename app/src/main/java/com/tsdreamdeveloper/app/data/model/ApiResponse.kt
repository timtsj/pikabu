package com.tsdreamdeveloper.app.data.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(@SerializedName("posts") val posts: T)