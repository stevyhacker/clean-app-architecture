package me.modernandroid.cleanapp.ui.screens.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import me.modernandroid.cleanapp.models.Repository
import me.modernandroid.cleanapp.repository.GitRepoRepository

class MainViewModel(private var gitRepoRepository: GitRepoRepository) : ViewModel() {

    val text = ObservableField<String>("old data")
    var repositories = MutableLiveData<ArrayList<Repository>>()
    val isLoading = ObservableField<Boolean>(false)
    private val compositeDisposable = CompositeDisposable()

    fun loadRepositories() {
        isLoading.set(true)
        compositeDisposable.add(gitRepoRepository
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Repository>>() {

                    override fun onError(e: Throwable) {
                        //todo
                    }

                    override fun onNext(data: ArrayList<Repository>) {
                        repositories.value = data
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}