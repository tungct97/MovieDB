package com.example.moviedb.ui.screen.favourite

import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentFavouriteBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.screen.MainActivity
import com.example.moviedb.ui.screen.detail.DetailFragment
import com.example.moviedb.ui.screen.detail.DetailViewModel
import com.example.moviedb.ui.screen.home.HomeAdapter
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    override fun initData(view: FragmentFavouriteBinding) {
        homeAdapter = HomeAdapter(::listener)
        initRecycler(view)
        getData()
    }

    private fun getData() {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            homeAdapter.submitList(it)
        })
    }

    lateinit var homeAdapter: HomeAdapter
    val viewModel by viewModel<DetailViewModel>()

    private fun initRecycler(view: FragmentFavouriteBinding) {
        recycler_favourite?.apply {
            adapter = homeAdapter
        }
    }

    companion object {
        const val TAG = "FAVOURITE_FRAGMENT"

        fun newInstance() = FavouriteFragment()
    }

    override val layoutId: Int = R.layout.fragment_favourite

    fun listener(movie: Movie) {
        val detail = DetailFragment.newInstance(movie)
        if (activity is MainActivity) {
            (activity as MainActivity).addChildFragment(detail, DetailFragment.TAG, true)
        }
    }
}
