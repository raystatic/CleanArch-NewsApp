package com.raystatic.inshortsclone

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf

object Utils {

    fun shareNewsUrl(context: Context, url:String?){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Checkout this news:\n$url")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share Via")
        startActivity(context,shareIntent, bundleOf())
    }

}