package com.themovieguide.data.sources.local.module

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.repository.LocalMovieImpl
import com.themovieguide.data.sources.local.repository.LocalMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule {
    @Provides
    fun providesLocalMovieRepository(db: AppDatabase): LocalMovieRepository = LocalMovieImpl(app = db)
}
