package com.example.moviedb.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun lanchDisposable(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
