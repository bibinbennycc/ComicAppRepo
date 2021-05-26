package com.codetest.comicapp.feature.repository

import android.util.Log
import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.model.ComicModel
import com.codetest.comicapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicRepository(private val apiService: ApiService): AppRepository {

    //Method get the current comic information using the api
    override suspend fun getCurrentComicInfo(): Resource<ComicModel> {
        var response: Resource<ComicModel>
        try {
            withContext(Dispatchers.IO) {
                val result = apiService.getCurrentComicInfo()
                response = Resource.Success(result)
            }
        } catch (exception: Exception) {
            response = Resource.Error("Something went wrong")
            Log.e("Comic App Error", exception.toString())
        }
        return response
    }
}