package com.codetest.comicapp.feature.model

import com.google.gson.annotations.SerializedName

data class ComicModel(
    val title: String,
    @SerializedName("img")
    val imageUrl: String,
    @SerializedName("alt")
    val subtitle: String
)