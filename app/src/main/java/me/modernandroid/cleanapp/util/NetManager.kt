package me.modernandroid.cleanapp.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetManager @Inject constructor(var applicationContext: Context) {
    private var status: Boolean = false

    val isConnectedToInternet: Boolean
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}