package com.raystatic.inshortsclone.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.raystatic.inshortsclone.R
import com.raystatic.inshortsclone.Utils
import com.raystatic.inshortsclone.databinding.FragmentHomeBinding
import com.raystatic.inshortsclone.presentation.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private val vm by viewModels<HomeViewModel>()

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.tvToday.text = Utils.getToday()

        newsAdapter = NewsAdapter(
            onClick = {news->
                val bundle = bundleOf(
                    "url" to news?.url
                )
                findNavController().navigate(R.id.action_homeFragment_to_newsDetailFragment,bundle)
            },
            onBookmarkedClick = {news,isBookmarked->
                news?.let {
                    if (isBookmarked) vm.addNewsToBookmark(it)
                    else vm.removeBookmark(it.title)
                }
            },
            onShare = {news->
                Utils.shareNewsUrl(requireContext(),news?.url)
            }
        )

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        binding.tvBookmarks.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookmarkedFragment)
        }

        subscribeToObservers()

        lifecycleScope.launch {
            newsAdapter.loadStateFlow.collectLatest {loadState->
                binding.progress.isVisible = loadState.refresh is LoadState.Loading
                if (loadState.refresh is LoadState.Error){
                    Toast.makeText(requireContext(), "Error: ${(loadState.refresh as LoadState.Error).error.localizedMessage}", Toast.LENGTH_SHORT).show()
                }

                binding.tvEmpty.isVisible = loadState.refresh is LoadState.NotLoading &&
                        loadState.refresh.endOfPaginationReached &&
                        newsAdapter.itemCount < 1

            }
        }

        vm.getTrendingNews("in")

    }

    private fun subscribeToObservers() {
        vm.news.observe(viewLifecycleOwner, {
            newsAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}