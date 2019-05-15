package com.example.moviedb.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executors

abstract class BaseAdapter<Item, ViewBinding : ViewDataBinding>(callback: DiffUtil.ItemCallback<Item>) :
    ListAdapter<Item, BaseViewHolder<ViewBinding>>(
        AsyncDifferConfig.Builder<Item>(callback).setBackgroundThreadExecutor(
            Executors.newSingleThreadExecutor()
        ).build()
    ) {
    override fun submitList(list: List<Item>?) {
        super.submitList(ArrayList<Item>(list ?: listOf()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(createBinding(parent, viewType))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int? = 0): ViewBinding

    protected abstract fun bind(binding: ViewBinding, item: Item)

    protected abstract fun getResLayout(viewType: Int?): Int
}
