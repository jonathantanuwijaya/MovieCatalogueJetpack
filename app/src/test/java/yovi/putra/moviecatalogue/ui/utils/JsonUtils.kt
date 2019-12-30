package yovi.putra.moviecatalogue.ui.utils

import com.google.gson.Gson
import java.io.File

object JsonUtils {
    fun getJson(path: String) : String {
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path!!)
        return String(file.readBytes())
    }

    fun <T> getJsonObject(path: String, typeOfClass: Class<T>) : T =
        Gson().fromJson(getJson(path), typeOfClass)
}
