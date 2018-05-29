package me.modernandroid.cleanapp.ui.screens.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import me.modernandroid.cleanapp.models.Repository
import me.modernandroid.cleanapp.repository.GitRepoRepository
import me.modernandroid.cleanapp.repository.OnRepositoryReadyCallback
import me.modernandroid.cleanapp.util.NetManager

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))

    val text = ObservableField<String>("old data")
    var repositories = MutableLiveData<ArrayList<Repository>>()
    val isLoading = ObservableField<Boolean>(false)

    fun loadRepositories() {
        isLoading.set(true)
        gitRepoRepository.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}