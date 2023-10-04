package com.example.newsapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("author")
    val author: String?=null,
    @SerializedName("content")
    val content: String?=null,
    @SerializedName("description")
    val description: String?=null,
    @SerializedName("publishedAt")
    val publishedAt: String?=null,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null,
    @SerializedName("urlToImage")
    val urlToImage: String?=null
) : Parcelable