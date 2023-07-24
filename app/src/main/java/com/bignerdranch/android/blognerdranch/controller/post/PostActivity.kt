package com.bignerdranch.android.blognerdranch.controller.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.BlogService
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.model.Post
import com.bignerdranch.android.blognerdranch.repository.PostRepository
import com.bignerdranch.android.blognerdranch.viewmodel.PostViewModel
import com.bignerdranch.android.blognerdranch.viewmodel.PostViewModelFactory

class PostActivity : AppCompatActivity() {

    private var postId: Int = 0

    private var postTitle: TextView? = null
    private var postAuthor: TextView? = null
    private var postBody: TextView? = null

    private val blogService = BlogService.getInstance()

    lateinit var viewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(PostRepository(blogService))
        )[PostViewModel::class.java]

        postTitle = findViewById(R.id.titleTextView)
        postAuthor = findViewById(R.id.authorTextView)
        postBody = findViewById(R.id.bodyTextView)

        postId = intent.getIntExtra(EXTRA_POST_ID, 0)

        viewModel.getPost(postId)

        viewModel.postResponse.observe(this, Observer {
            updateUI(it)
        })

        viewModel.postErrorMessage.observe(this, Observer {
            Log.e(TAG, "Failed to load post  $it")
        })
    }

    private fun updateUI(post: Post) {
        postTitle?.text = post.metadata?.title
        postAuthor?.text = post.metadata?.author?.name
        postBody?.text = post.body
    }

    companion object {
        const val TAG = "PostActivity"

        const val EXTRA_POST_ID = "postID"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, id)
            return intent
        }
    }
}
