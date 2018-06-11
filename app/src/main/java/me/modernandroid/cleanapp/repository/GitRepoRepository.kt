package me.modernandroid.cleanapp.repository

import io.reactivex.Observable
import me.modernandroid.cleanapp.models.Repository
import me.modernandroid.cleanapp.util.NetManager

class GitRepoRepository(val netManager: NetManager) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    fun getRepositories(): Observable<ArrayList<Repository>> {
        netManager.isConnectedToInternet.let {
            if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource.saveRepositories(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            }
        }
        return localDataSource.getRepositories()

    }
}