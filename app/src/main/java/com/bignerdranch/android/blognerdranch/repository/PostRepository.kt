package com.bignerdranch.android.blognerdranch.repository

import com.bignerdranch.android.blognerdranch.BlogService

class PostRepository constructor(private val blogService: BlogService) {

    fun getPostMetadata() =
        blogService.getPostMetadata()

    fun getPost(id: Int) =
        blogService.getPost(id)
}