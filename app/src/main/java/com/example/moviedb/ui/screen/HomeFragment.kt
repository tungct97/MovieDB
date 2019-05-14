package com.example.moviedb.ui.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val viewmodel by viewModel<MovieViewModel>()

    companion object {
        const val TAG = "HOME_FRAGMENT"

        fun newInstance() = HomeFragment()
    }

    override val layoutId: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getMovies(1).observe(viewLifecycleOwner, Observer<MovieResponse> {

        })
    }
}
