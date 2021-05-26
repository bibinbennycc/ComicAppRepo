package com.codetest.comicapp.feature.repository

import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.model.ComicModel

interface AppRepository {
    suspend fun getCurrentComicInfo(): Resource<ComicModel>
}