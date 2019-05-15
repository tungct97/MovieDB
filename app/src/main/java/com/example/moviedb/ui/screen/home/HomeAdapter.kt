package com.example.moviedb.ui.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.ui.base.BaseAdapter

class HomeAdapter(var listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title && oldItem.posterPath == newItem.posterPath
        }
    }) {
    override fun bind(binding: ItemMovieBinding, item: Movie) {
        binding.movie = item
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ItemMovieBinding {
        return DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            getResLayout(viewType),
            parent,
            false
        ).apply {
            root.setOnClickListener {
                movie?.let { (listener.invoke(it)) }
            }
        }
    }

    override fun getResLayout(viewType: Int?): Int = R.layout.item_movie
}
