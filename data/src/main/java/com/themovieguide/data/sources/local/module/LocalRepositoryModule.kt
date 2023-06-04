package com.themovieguide.data.sources.local.module

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.repository.theater.InTheaterDBRepository
import com.themovieguide.data.sources.local.repository.theater.TheaterDBImpl
import com.themovieguide.data.sources.local.repository.visited.DBMovieImpl
import com.themovieguide.data.sources.local.repository.visited.DBMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule {
    @Provides
    fun providesLocalMovieRepository(db: AppDatabase): DBMovieRepository = DBMovieImpl(app = db)

    @Provides
    fun provideLocalInTheaterRepository(db: AppDatabase): InTheaterDBRepository = TheaterDBImpl(app = db)
}
