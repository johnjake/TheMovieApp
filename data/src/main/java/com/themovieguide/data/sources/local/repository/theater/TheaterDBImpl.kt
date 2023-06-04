package com.themovieguide.data.sources.local.repository.theater

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.TheaterDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheaterDBImpl @Inject constructor(
    private var app: AppDatabase,
) : InTheaterDBRepository {
    override fun insertMovie(movie: TheaterDB) {
        app.theaterDao().insertMovie(movie)
    }

    override fun getMovies(): Flow<List<TheaterDB>> {
        return app.theaterDao().getMovies()
    }

    override fun getMoviesByTitle(): Flow<List<TheaterDB>> {
        return app.theaterDao().getMoviesByTitle()
    }

    override suspend fun getMovieById(movieId: Int): TheaterDB {
        return app.theaterDao().getMovieById(movieId)
    }

    override suspend fun deleteMovie(movieId: Int) {
        app.theaterDao().deleteMovie(movieId)
    }
}
