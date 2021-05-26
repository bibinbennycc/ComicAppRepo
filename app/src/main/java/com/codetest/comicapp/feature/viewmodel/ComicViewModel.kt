package com.codetest.comicapp.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.model.ComicModel
import com.codetest.comicapp.feature.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private val _currentComicInfo: MutableLiveData<Resource<ComicModel>> = MutableLiveData()
    val currentComic: LiveData<Resource<ComicModel>>
        get() = _currentComicInfo

    //Method get the current comic information from the repository
    fun getCurrentComicInfo() {
        viewModelScope.launch {
            val result = repository.getCurrentComicInfo()
            setCurrentComicInfo(result)
        }
    }

    private fun setCurrentComicInfo(result: Resource<ComicModel>) {
        _currentComicInfo.value = result
    }
}