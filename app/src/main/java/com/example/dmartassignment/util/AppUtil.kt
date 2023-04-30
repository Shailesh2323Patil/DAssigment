package com.example.dmartassignment.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.dmartassignment.R

object AppUtil {
    fun loadImage(imageUrl : String, imageView : ImageView, context : Context) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.place_holder)
            .into(imageView);
    }
}