package yovi.putra.moviecatalogue.core.utils.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import yovi.putra.moviecatalogue.R

class LoadingController(private val activity: Activity) {

    private var dialog: AlertDialog? = null

    @SuppressLint("InflateParams")
    fun show() {
        val dialogBuilder = AlertDialog.Builder(activity)
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.progress_dialog_layout, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        dialog = dialogBuilder.create()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()
    }

    fun hide() {
        dialog?.dismiss()
    }
}