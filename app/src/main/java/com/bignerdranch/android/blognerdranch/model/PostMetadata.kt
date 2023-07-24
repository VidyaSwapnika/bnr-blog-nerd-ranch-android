package com.bignerdranch.android.blognerdranch.model

data class PostMetadata(
    val postId: Int,
    val title: String,
    val summary: String,
    val author: Author,
    val publishDate: String
)