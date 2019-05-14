package com.example.moviedb.ui.screen.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.screen.MainActivity
import com.example.moviedb.ui.screen.MovieViewModel
import com.example.moviedb.ui.screen.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var homeAdapter: HomeAdapter
    val viewModel by viewModel<MovieViewModel>()

    companion object {
        const val TAG = "HOME_FRAGMENT"

        fun newInstance() = HomeFragment()
    }

    override val layoutId: Int = R.layout.fragment_home

    override fun initData(view: FragmentHomeBinding) {
        homeAdapter = HomeAdapter(::listener)
        getData()
        initRecycler(view)
    }

    private fun initRecycler(view: FragmentHomeBinding) {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view.recyclerMovie.layoutManager = staggeredGridLayoutManager
        view.recyclerMovie.adapter = homeAdapter
    }

    private fun getData() {
        viewModel.getMovies(1).observe(viewLifecycleOwner, Observer<MovieResponse> {
            homeAdapter.submitList(it.results)
        })
    }

    private fun listener(movie: Movie) {
        val detail = DetailFragment.newInstance()
        if (activity is MainActivity)
            (activity as MainActivity).addFragment(detail, DetailFragment.TAG)
    }
}