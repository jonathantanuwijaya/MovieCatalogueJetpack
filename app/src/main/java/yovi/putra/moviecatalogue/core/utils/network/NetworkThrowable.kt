package yovi.putra.moviecatalogue.core.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import org.json.JSONObject
import retrofit2.HttpException
import yovi.putra.moviecatalogue.R

object NetworkThrowable {
    fun errorMessage(context: Context, throwable: Throwable?): String =
        when (throwable) {
            is HttpException -> {
                try {
                    val errBody = throwable.response()?.errorBody()?.string()
                    val json = JSONObject(errBody!!)
                    json["status_message"].toString()
                } catch (e: Exception) {
                    Log.e(javaClass.simpleName, "", e)
                    context.getString(R.string.server_under_maintenance)
                }
            }
            else -> {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                if (isConnected) {
                    context.getString(R.string.cannot_connect_toserver)
                } else {
                    context.getString(R.string.no_internet_connection)
                }
            }
        }
}