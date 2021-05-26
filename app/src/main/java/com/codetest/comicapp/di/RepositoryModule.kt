package com.codetest.comicapp.di

import com.codetest.comicapp.feature.repository.AppRepository
import com.codetest.comicapp.feature.repository.ComicRepository
import com.codetest.comicapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesAppRepository(apiService: ApiService): AppRepository {
        return ComicRepository(apiService)
    }
}