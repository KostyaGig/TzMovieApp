package com.zinoview.tzmovieapp.core

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zinoview.tzmovieapp.presentation.core.log

interface BaseImage {

    fun loadTo(imageView: ImageView)

    data class Base(
        private val imageUrl: String
    ) : BaseImage {

        override fun loadTo(imageView: ImageView) {
            log("loadTo url $imageUrl")
            Picasso.get().load(imageUrl).into(imageView)
        }
    }
}