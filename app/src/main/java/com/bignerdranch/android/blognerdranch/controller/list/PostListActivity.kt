package com.bignerdranch.android.blognerdranch.controller.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.BlogService
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.repository.PostRepository
import com.bignerdranch.android.blognerdranch.viewmodel.PostViewModel
import com.bignerdranch.android.blognerdranch.viewmodel.PostViewModelFactory

class PostListActivity : AppCompatActivity() {

    private var postRecyclerView: RecyclerView? = null

    private val blogService = BlogService.getInstance()

    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        postRecyclerView = findViewById(R.id.post_recyclerview)
        postRecyclerView?.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(PostRepository(blogService))
        )[PostViewModel::class.java]

        viewModel.getPostMetaData()

        viewModel.postMetaDataResponse.observe(this, Observer {
            postRecyclerView?.adapter = PostAdapter(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.e(TAG, "Failed to load postMetadata  $it")
        })

    }

    companion object {
        const val TAG = "PostListActivity"
    }
}
