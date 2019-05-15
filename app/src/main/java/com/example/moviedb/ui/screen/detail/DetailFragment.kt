package com.example.moviedb.ui.screen.detail

import android.os.Bundle
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentDetailBinding
import com.example.moviedb.ui.base.BaseFragment
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

    val viewModel by viewModel<DetailViewModel>()

    override val layoutId: Int = R.layout.fragment_detail

    override fun initData(view: FragmentDetailBinding) {
        view.viewmodel = viewModel.apply {
            loadData(arguments?.getParcelable(ARGUMENT_MOVIE) as Movie)
        }
    }
}
