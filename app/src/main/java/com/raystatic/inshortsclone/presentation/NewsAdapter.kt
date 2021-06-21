package com.raystatic.inshortsclone.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raystatic.domain.model.News
import com.raystatic.inshortsclone.databinding.ItemNewsBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(news:News, position:Int){
            binding.apply {
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(25,3)))
                    .into(imageBackground)
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .into(image)

                tvTitle.text = news.title
                tvContent.text = news.content ?: news.description
                tvDescription.text = news.description
                tvSource.text = news.source?.let { "Source $it" }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: News,
            newItem: News
        ) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitData(list: List<News>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position],position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}