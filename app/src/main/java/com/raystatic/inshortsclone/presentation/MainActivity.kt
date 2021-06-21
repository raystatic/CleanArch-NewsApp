package com.raystatic.inshortsclone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.raystatic.domain.Resource
import com.raystatic.inshortsclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var newsAdapter: NewsAdapter

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter()

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        subscribeToObservers()

        vm.getTrendingNews("in")

    }

    private fun subscribeToObservers() {
        vm.news.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.progress.isVisible = false
                    it.data?.let { it1 -> newsAdapter.submitData(it1) }
                    Toast.makeText(this, "Data loaded", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    binding.progress.isVisible = false
                    Toast.makeText(this, "Error ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.isVisible = true
                }
            }
        })
    }
}