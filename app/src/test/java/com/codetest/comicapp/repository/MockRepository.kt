package com.codetest.comicapp.repository

import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.model.ComicModel
import com.codetest.comicapp.feature.repository.AppRepository

class MockRepository : AppRepository {

    var isReturnError = false

    override suspend fun getCurrentComicInfo(): Resource<ComicModel> {
        return if (!isReturnError) {
            Resource.Success(ComicModel("Test Title", "test_url", "Test description"))
        } else {
            Resource.Error("Something went wrong")
        }
    }
}