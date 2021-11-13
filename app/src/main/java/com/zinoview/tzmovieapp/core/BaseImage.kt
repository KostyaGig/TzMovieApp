package com.zinoview.tzmovieapp.core

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface BaseImage {

    fun loadTo(imageView: ImageView)

    data class Base(
        private val imageUrl: String
    ) : BaseImage {

        override fun loadTo(imageView: ImageView)
            = Picasso.get().load(imageUrl).into(imageView)

    }
}