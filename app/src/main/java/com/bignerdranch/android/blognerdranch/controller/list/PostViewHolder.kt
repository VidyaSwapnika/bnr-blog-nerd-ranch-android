package com.bignerdranch.android.blognerdranch.controller.list

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.model.PostMetadata
import com.bignerdranch.android.blognerdranch.controller.post.PostActivity

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var postMetadata: PostMetadata? = null

    private val titleTextView: TextView = itemView.findViewById(R.id.titleText)
    private val authorTextView: TextView = itemView.findViewById(R.id.authorText)
    private val summaryTextView: TextView = itemView.findViewById(R.id.summaryText)
    private val publishDateText: TextView = itemView.findViewById(R.id.publishDateText)

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(postMetadata: PostMetadata) {
        this.postMetadata = postMetadata
        titleTextView.text = postMetadata.title
        authorTextView.text = postMetadata.author?.title
        summaryTextView.text = postMetadata.summary
        publishDateText.text = postMetadata.publishDate
    }

    override fun onClick(v: View) {
        val context = v.context
        context.startActivity(PostActivity.newIntent(v.context, postMetadata!!.postId!!))
    }

}