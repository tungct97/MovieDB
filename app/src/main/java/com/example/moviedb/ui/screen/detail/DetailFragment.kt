package com.example.moviedb.ui.screen.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentDetailBinding
import com.example.moviedb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    companion object {
        const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"
        const val TAG = "DETAIL_FRAGMENT"

        fun newInstance(movie: Movie) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_MOVIE, movie)
            }
        }
    }

    var movie = Movie()

    val viewModel by viewModel<DetailViewModel>()

    override val layoutId: Int = R.layout.fragment_detail

    override fun initData(view: FragmentDetailBinding) {
        movie = arguments?.getParcelable(ARGUMENT_MOVIE) as Movie
        view.viewmodel = viewModel.apply {
            loadData(movie)
        }
    }
}
