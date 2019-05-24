package com.example.moviedb.ui.screen.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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
        if (isNetworkAvailable() == false) {
            Toast.makeText(activity, "Bạn không có kết nối mạng", Toast.LENGTH_SHORT).show()
            return
        }
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
        viewModel.apply {
            message.observe(viewLifecycleOwner, Observer {
                when(it) {
                    true -> {
                        Toast.makeText(context, "Loi ket noi", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
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

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun getData() {
        viewModel.getMovies()
    }

    private fun listener(movie: Movie) {
        val detail = DetailFragment.newInstance(movie)
        if (activity is MainActivity) {
            (activity as MainActivity).addChildFragment(detail, DetailFragment.TAG, true)
        }
    }

    override fun onRefresh() {
        if (isNetworkAvailable() == false) {
            Toast.makeText(activity, "Bạn không có kết nối mạng", Toast.LENGTH_SHORT).show()
            refresh_layout.isRefreshing = false
            return
        }
        viewModel.refreshLayout()
        refresh_layout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}
