package com.raystatic.inshortsclone

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getToday():String{
        val  df = SimpleDateFormat("dd MMM yyy", Locale.getDefault()) // Quoted "Z" to indicate UTC, no timezone offset
        return df.format(Date())
    }

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