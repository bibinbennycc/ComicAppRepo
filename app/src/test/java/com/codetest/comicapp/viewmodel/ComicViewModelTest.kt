package com.codetest.comicapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codetest.comicapp.MainCoroutineRule
import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.viewmodel.ComicViewModel
import com.codetest.comicapp.getOrAwaitValueTest
import com.codetest.comicapp.repository.MockRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ComicViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var comicViewModel: ComicViewModel
    private lateinit var repository: MockRepository

    @Before
    fun setUp() {
        repository = MockRepository()
        comicViewModel = ComicViewModel(repository)
    }

    @Test
    fun testGetCurrentComicInfoReturnsSuccess() =mainCoroutineRule.runBlockingTest {
        comicViewModel.getCurrentComicInfo()
        val result = comicViewModel.currentComic.getOrAwaitValueTest()
        assertThat(result is Resource.Success).isEqualTo(true)
    }

    @Test
    fun testGetCurrentComicInfoReturnsError() =mainCoroutineRule.runBlockingTest {
        repository.isReturnError = true
        comicViewModel.getCurrentComicInfo()
        val result = comicViewModel.currentComic.getOrAwaitValueTest()
        assertThat(result is Resource.Error).isEqualTo(true)
    }
}