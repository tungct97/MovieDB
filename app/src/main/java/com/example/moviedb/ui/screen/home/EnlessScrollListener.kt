package com.example.moviedb.ui.screen.home

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EnlessScrollListener(private val loadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is StaggeredGridLayoutManager
            && layoutManager.findLastVisibleItemPositions(
                IntArray(layoutManager.spanCount)
            )[0] == layoutManager.itemCount - 2
        ) {
            loadMore()
        }
    }
}
