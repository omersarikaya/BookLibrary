package com.omersarikaya.booklibrary.view.dialog

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.databinding.DialogBookDetailBinding

class FullScreenDialog(private val activity: Activity) {

    fun show() {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val binding: DialogBookDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.dialog_book_detail,
            null,
            false
        )

        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        dialog.show()
    }
}
