package com.raystatic.inshortsclone.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.raystatic.domain.Resource
import com.raystatic.inshortsclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var newsAdapter: NewsAdapter

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter{news ->
            val intent = Intent(this,NewsWebActivity::class.java)
            intent.putExtra("url",news?.url)
            startActivity(intent)
        }

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        subscribeToObservers()

        lifecycleScope.launch {
            newsAdapter.loadStateFlow.collectLatest {loadState->
                binding.progress.isVisible = loadState.refresh is LoadState.Loading
                if (loadState.refresh is LoadState.Error){
                    Toast.makeText(this@MainActivity, "Error: ${(loadState.refresh as LoadState.Error).error.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        vm.getTrendingNews("in")

    }

    private fun subscribeToObservers() {
        vm.news.observe(this, {
            newsAdapter.submitData(lifecycle,it)
        })
    }
}