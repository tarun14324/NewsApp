package com.example.newsapp.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.Article
import com.example.newsapp.utils.ViewUtils
import com.example.newsapp.utils.ViewUtils.extractPublishedTime
import com.example.newsapp.utils.hide


class NewsAdapter(
    val onItemClicked: (Article) -> Unit,
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Article>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult")
        fun bind(data: Article) {
            binding.item = data
            binding.tvDate.text = data.publishedAt?.let { extractPublishedTime(it) }

            binding.root.setOnClickListener {
                onItemClicked(list[adapterPosition])
            }
            val requestOptions = RequestOptions()
            requestOptions.placeholder(ViewUtils.randomDrawbleColor)
            requestOptions.error(ViewUtils.randomDrawbleColor)
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
            requestOptions.centerCrop()

            Glide.with(binding.image.context)
                .load(data.urlToImage)
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loader.hide()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable?>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loader.hide()
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.image)
        }
    }
}