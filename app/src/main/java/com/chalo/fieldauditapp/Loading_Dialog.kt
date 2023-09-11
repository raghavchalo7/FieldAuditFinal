package com.chalo.fieldauditapp

import android.app.Activity
import android.app.AlertDialog

class Loading_Dialog(val mActivity:Activity) {
    private lateinit var isDialog:AlertDialog
    fun start(){
        val inflater=mActivity.layoutInflater
        val dialogView=inflater.inflate(R.layout.loading_bar,null)
        val builder=AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog=builder.create()
        isDialog.show()
    }

    fun isDismiss(){
        isDialog.dismiss()
    }
}