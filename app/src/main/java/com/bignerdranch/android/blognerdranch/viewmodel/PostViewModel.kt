package com.bignerdranch.android.blognerdranch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.model.Post
import com.bignerdranch.android.blognerdranch.model.PostMetadata
import com.bignerdranch.android.blognerdranch.repository.PostRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel constructor(private val repository: PostRepository) : ViewModel() {

    val postMetaDataResponse = MutableLiveData<List<PostMetadata>>()
    val postResponse = MutableLiveData<Post>()
    val errorMessage = MutableLiveData<String>()
    val postErrorMessage = MutableLiveData<String>()

    fun getPostMetaData() {

        val response = repository.getPostMetadata()

        response.enqueue(object : Callback<List<PostMetadata>> {
            override fun onResponse(
                call: Call<List<PostMetadata>>,
                response: Response<List<PostMetadata>>
            ) {
                postMetaDataResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PostMetadata>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }

    fun getPost(id: Int) {
        val response = repository.getPost(id)

        response.enqueue(object : Callback<Post> {
            override fun onResponse(
                call: Call<Post>,
                response: Response<Post>
            ) {
                postResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                postErrorMessage.postValue(t.message)
            }
        })
    }

}