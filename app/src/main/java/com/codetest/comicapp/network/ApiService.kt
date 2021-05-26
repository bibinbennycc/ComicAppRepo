package com.codetest.comicapp.network

import com.codetest.comicapp.feature.model.ComicModel
import retrofit2.http.GET

interface ApiService {

    @GET("info.0.json")
    suspend fun getCurrentComicInfo() : ComicModel
}