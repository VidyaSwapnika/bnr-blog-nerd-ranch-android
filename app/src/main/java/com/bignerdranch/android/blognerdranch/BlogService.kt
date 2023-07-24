package com.bignerdranch.android.blognerdranch

import com.bignerdranch.android.blognerdranch.model.Post
import com.bignerdranch.android.blognerdranch.model.PostMetadata
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService {

    @GET("post-metadata")
    fun getPostMetadata(): Call<List<PostMetadata>>

    @GET("post/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    companion object {
        private var blogService: BlogService? = null

        fun getInstance(): BlogService {
            if (blogService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8106/") // "localhost" is the emulator's host. 10.0.2.2 goes to your computer
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                blogService = retrofit.create(BlogService::class.java)
            }
            return blogService!!
        }
    }
}