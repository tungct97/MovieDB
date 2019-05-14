package com.example.moviedb.ui.screen.detail

import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentDetailBinding
import com.example.moviedb.ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    companion object {
        const val TAG = "DETAIL_FRAGMENT"

        fun newInstance() = DetailFragment()
    }

    override val layoutId: Int = R.layout.fragment_detail

    override fun initData(view: FragmentDetailBinding) {

    }
}
