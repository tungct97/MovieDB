package com.example.moviedb.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class BaseViewHolder<ViewBinding : ViewDataBinding> constructor(
    val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root)
