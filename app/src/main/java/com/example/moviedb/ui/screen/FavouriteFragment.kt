package com.example.moviedb.ui.screen

import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentFavouriteBinding
import com.example.moviedb.ui.base.BaseFragment

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    override fun initData(view: FragmentFavouriteBinding) {
    }

    companion object {
        const val TAG = "FAVOURITE_FRAGMENT"

        fun newInstance() = FavouriteFragment()
    }

    override val layoutId: Int = R.layout.fragment_favourite
}
