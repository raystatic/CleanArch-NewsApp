package com.raystatic.inshortsclone.presentation.newsdetail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raystatic.inshortsclone.R
import com.raystatic.inshortsclone.databinding.FragmentBookmarkedBinding
import com.raystatic.inshortsclone.databinding.FragmentWebviewBinding

class NewsDetailFragment: Fragment(R.layout.fragment_webview) {

    private var _binding: FragmentWebviewBinding?=null
    private val binding get() = _binding!!

    private var link = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWebviewBinding.bind(view)

        link = arguments?.getString("url") ?: ""

        if (link.isEmpty()) findNavController().navigateUp()

        val title = link.split("//")[1].split("/")[0]

        binding.tvTitle.text = title

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(link)
            isVerticalScrollBarEnabled = true
            isHorizontalScrollBarEnabled = true
            webViewClient = object : WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        binding.progress.isVisible = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progress.isVisible = false
                }
            }
        }


    }

}