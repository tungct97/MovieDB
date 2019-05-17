package com.example.moviedb.ui.screen.home

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.screen.MainActivity
import com.example.moviedb.ui.screen.MovieViewModel
import com.example.moviedb.ui.screen.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var homeAdapter: HomeAdapter
    val viewModel by viewModel<MovieViewModel>()
    private val onEnlessScrollListener = EnlessScrollListener(::onLoadMore)

    private fun onLoadMore() {
        if (viewModel.loadmore.value == false) {
            viewModel.reloadingMovie()
        }
    }

    companion object {
        const val TAG = "HOME_FRAGMENT"

        fun newInstance() = HomeFragment()
    }

    override val layoutId: Int = R.layout.fragment_home

    override fun initData(view: FragmentHomeBinding) {
        viewBinding.viewModel = viewModel
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            homeAdapter.submitList(it)
        })
        homeAdapter = HomeAdapter(::listener)
        initRecycler(view)
        getData()
    }

    private fun initRecycler(view: FragmentHomeBinding) {
        refresh_layout.setOnRefreshListener(this)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_movie?.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = homeAdapter
            addOnScrollListener(onEnlessScrollListener)
        }
    }

    private fun getData() {
        viewModel.getMovies()
    }

    private fun listener(movie: Movie) {
        val detail = DetailFragment.newInstance(movie)
        if (activity is MainActivity)
            (activity as MainActivity).addFragment(detail, DetailFragment.TAG)
    }

    override fun onRefresh() {
        viewModel.refreshLayout()
        refresh_layout.isRefreshing = false
    }
}
