package yovi.putra.moviecatalogue.core.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import retrofit2.HttpException

/**
 * Created by yovi.putra
 * on 09/Mar/2019 10:56
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override val contextView: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupLayoutId())
        setupData()
        setupUI()
    }

    /*fun error(thowable: Throwable): String =
        when (thowable) {
            is HttpException -> {
                try {
                    val errBody = thowable.response().errorBody()?.string()
                    val json = JSONObject(errBody)
                    view?.onPushInformation(json["status_message"].toString(), null)
                } catch (e: Exception) {
                    Log.e(TAG, e.message)
                    return R.string.server_under_maintenance
                }
            }
            else -> {
                val connectivityManager = view?.contextView()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                if (isConnected) {
                    view?.onPushInformation(view?.contextView()?.getString(R.string.cannot_connect_toserver), null)
                } else {
                    view?.onPushInformation(view?.contextView()?.getString(R.string.no_internet_connection), null)
                }
            }
        }
}
*/
abstract fun setupLayoutId() : Int

    abstract fun setupData()

    abstract fun setupUI()

    override fun onShowLoader() {}

    override fun onHideLoader() {}
}