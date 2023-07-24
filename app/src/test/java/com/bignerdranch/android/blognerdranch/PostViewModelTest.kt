package com.bignerdranch.android.blognerdranch

import com.bignerdranch.android.blognerdranch.model.Author
import com.bignerdranch.android.blognerdranch.model.Post
import com.bignerdranch.android.blognerdranch.model.PostMetadata
import com.bignerdranch.android.blognerdranch.repository.PostRepository
import com.bignerdranch.android.blognerdranch.viewmodel.PostViewModel
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostViewModelTest {

    private val postRepository: PostRepository = mockk()
    private lateinit var viewModel: PostViewModel

    @Before
    fun setup() {
        viewModel = PostViewModel(postRepository)
    }

    @Test
    fun getPostMetaDataTestSuccess() {
        viewModel.getPostMetaData()
        val author = Author("Author", "imageurl", "title")
        val postMetadataList = listOf(PostMetadata(1, "Title1", "Title Summary", author, "20230715"), PostMetadata(2, "Title 2", "Title Summary", author, "20230715"))

        Assert.assertEquals(postMetadataList, viewModel.postMetaDataResponse.value)
    }

    @Test
    fun getPostMetaDataTestFailure() {
        viewModel.getPostMetaData()
        val errorMessage = "Network error"
        Assert.assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    @Test
    fun getPostTestSuccess() {
        val postId = 1
        val author = Author("Author", "imageurl", "title")
        val postMetadata = PostMetadata(1, "Title1", "Title Summary", author, "20230715")
        val post = Post(postId, postMetadata, "Post Body")
        viewModel.getPost(postId)
        Assert.assertEquals(post, viewModel.postResponse.value)
    }

    @Test
    fun getPostTestFailure() {
        val postId = 1
        viewModel.getPost(postId)
        val errorMessage = "Network error"
        Assert.assertEquals(errorMessage, viewModel.postErrorMessage.value)
    }

}