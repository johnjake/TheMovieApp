package com.themovieguide.data.sources.local.repository

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.MovieDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMovieImpl @Inject constructor(private var app: AppDatabase) : LocalMovieRepository {
    override fun insertMovie(movie: MovieDB) {
        app.movieDao().insertMovie(movie = movie)
    }

    override fun getMovies(): Flow<List<MovieDB>> {
        return app.movieDao().getMovies()
    }

    override fun getMoviesByTitle(): Flow<List<MovieDB>> {
        return app.movieDao().getMoviesByTitle()
    }

    override suspend fun getMovieById(movieId: Int): MovieDB {
        return app.movieDao().getMovieById(movieId = movieId)
    }

    override suspend fun deleteMovie(movieId: Int) {
        app.movieDao().deleteMovie(movieId = movieId)
    }
}
