package com.raystatic.inshortsclone.presentation.bookmarked

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raystatic.domain.Resource
import com.raystatic.inshortsclone.R
import com.raystatic.inshortsclone.Utils
import com.raystatic.inshortsclone.databinding.FragmentBookmarkedBinding
import com.raystatic.inshortsclone.presentation.adapters.BookmarkedNewsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookmarkedFragment: Fragment(R.layout.fragment_bookmarked) {

    private var _binding: FragmentBookmarkedBinding?=null
    private val binding get() = _binding!!

    private lateinit var bookmarkedNewsAdapter:BookmarkedNewsAdapter

    private val vm by viewModels<BookmarkedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBookmarkedBinding.bind(view)

        bookmarkedNewsAdapter = BookmarkedNewsAdapter(
            onClick = {news->
                val bundle = bundleOf(
                    "url" to news?.url
                )
                findNavController().navigate(R.id.action_bookmarkedFragment_to_newsDetailFragment,bundle)
            },
            onUnBookmarkedClick = {news->
                news?.let {
                    vm.removeBookmark(it.title)
                }
            },
            onShare = {news->
                Utils.shareNewsUrl(requireContext(),news?.url)
            }
        )

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarkedNewsAdapter
        }

        binding.imagBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvClear.setOnClickListener {
            showConfirmationDialog()
        }

        subscribeToObservers()

        vm.getBookmarkedNews()

    }

    private fun showConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("Delete all Bookmarks")
            setMessage("Are you sure to delete all bookmarks?")
            setCancelable(true)
            setPositiveButton("Yes") { dialog, which ->
                vm.clearBookmarks()
                dialog.cancel()
            }

            setNegativeButton("No"){dialog, which ->
                dialog.cancel()
            }

        }
    }

    private fun subscribeToObservers() {
        vm.bookmarkedNews.observe(viewLifecycleOwner, {
            binding.tvEmpty.isVisible = it.isNullOrEmpty()
            it?.let { it1 -> bookmarkedNewsAdapter.submitData(it1) }

        })
    }

}