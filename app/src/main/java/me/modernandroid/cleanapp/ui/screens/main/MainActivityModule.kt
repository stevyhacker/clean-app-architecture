package me.modernandroid.cleanapp.ui.screens.main

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import me.modernandroid.cleanapp.repository.GitRepoRepository

@Module
internal abstract class MainActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesMainViewModelFactory(gitRepoRepository: GitRepoRepository)
                : MainViewModelFactory {
            return MainViewModelFactory(gitRepoRepository)
        }
    }


    @ContributesAndroidInjector()
    internal abstract fun mainActivity(): MainActivity

}