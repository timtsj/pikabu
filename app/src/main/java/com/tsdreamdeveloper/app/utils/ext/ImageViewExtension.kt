package com.tsdreamdeveloper.app.utils.ext

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tsdreamdeveloper.app.R

fun ImageView.load(url: String) {
    Picasso.get()
            .load(url)
            .fit().centerCrop()
            .error(R.drawable.ic_photo_camera)
            .into(this)
}

fun ImageView.loadWithRes(url: String, res: Int) {
    Picasso.get()
            .load(url)
            .placeholder(res)
            .into(this)
}

fun ImageView.loadImageTransition(url: String, onLoadEnd: OnLoadEnd) {
    Picasso.get()
            .load(url)
            .fit().centerCrop()
            .into(this, object : Callback {
                override fun onSuccess() {
                    onLoadEnd.onEndLoad()
                }

                override fun onError(e: Exception?) {
                    onLoadEnd.onEndLoad()
                }
            })
}

interface OnLoadEnd {
    fun onEndLoad()
}