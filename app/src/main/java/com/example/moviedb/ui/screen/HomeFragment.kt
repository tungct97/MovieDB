package com.example.moviedb.ui.screen

import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        const val TAG = "HOME_FRAGMENT"

        fun newInstance() = HomeFragment()
    }

    override val layoutId: Int = R.layout.fragment_home
}
