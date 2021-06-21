package com.raystatic.inshortsclone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raystatic.inshortsclone.databinding.ActivityNewsWebBinding

class NewsWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsWebBinding

    private var link = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        link = intent.getStringExtra("url") ?: ""

        if (link.isEmpty()) finish()

        val title = link.split("//")[1].split("/")[0]

        binding.tvTitle.text = title

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(link)
            isVerticalScrollBarEnabled = true
            isHorizontalScrollBarEnabled = true
        }

    }
}