package com.example.moviedb.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageSrc")
fun imageSrc(view: ImageView, url: String?) {
    Glide.with(view.context).load(Constant.PATH_IMAGE + url).into(view)
}
