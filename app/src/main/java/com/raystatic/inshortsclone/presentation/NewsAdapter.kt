package com.raystatic.inshortsclone.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raystatic.domain.model.News
import com.raystatic.inshortsclone.R
import com.raystatic.inshortsclone.databinding.ItemNewsBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class NewsAdapter(
    private val onClick:(News?)->Unit
):PagingDataAdapter<News,NewsAdapter.NewsViewHolder>(NewsComparator) {

    object NewsComparator :DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: News,
            newItem: News
        ) = oldItem == newItem
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(news:News?, position:Int){

            binding.apply {

                Glide.with(itemView)
                    .load(news?.urlToImage)
                    .placeholder(R.drawable.image_placeholder)
                    .into(image)

                Glide.with(itemView)
                    .load(news?.urlToImage)
                    .placeholder(R.drawable.image_placeholder)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(25,4)))
                    .into(imageBackground)



                tvTitle.text = news?.title
                tvContent.text =  news?.description
                tvDescription.text = news?.description
                tvSource.text = news?.source?.let { "Source $it" }

                tvTitle.setOnClickListener {
                    onClick(news)
                }

                viewBottom.setOnClickListener {
                    onClick(news)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }
}