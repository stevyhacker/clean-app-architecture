package me.modernandroid.cleanapp.ui.screens.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.modernandroid.cleanapp.repository.GitRepoRepository

class MainViewModelFactory(private val repository: GitRepoRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}