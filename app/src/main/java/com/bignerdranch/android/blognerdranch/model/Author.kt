package com.bignerdranch.android.blognerdranch.model

import com.google.gson.annotations.SerializedName

data class Author(
    val name: String,
    @SerializedName("image") val imageUrl: String,
    val title: String
)
