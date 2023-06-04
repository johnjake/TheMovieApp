package com.themovieguide.data.sources.local.repository.theater

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.TheaterDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheaterDBImpl @Inject constructor(
    private var app: AppDatabase,
) : InTheaterDBRepository {
    override fun insertMovie(movie: TheaterDB) {
        app.theaterDao().insertMovie(movie)
    }

    override fun insertInTheater(movie: TheaterDB) {
        app.theaterDao().insertOnTheater(
            movieKey = movie.movieKey,
            adult = movie.adult ?: false,
            backdropPath = movie.backdropPath ?: EMPTY,
            genreIds = movie.genreIds ?: EMPTY,
            id = movie.id ?: 0,
            originalLanguage = movie.originalLanguage ?: EMPTY,
            originalTitle = movie.originalTitle ?: EMPTY,
            overview = movie.overview ?: EMPTY,
            popularity = movie.popularity ?: 0.0,
            posterPath = movie.posterPath ?: EMPTY,
            releaseDate = movie.releaseDate ?: EMPTY,
            title = movie.title ?: EMPTY,
            video = movie.video ?: false,
            voteAverage = movie.voteAverage ?: 0.0,
            voteCount = movie.voteCount ?: 0,
        )
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

    override suspend fun delete() {
        app.theaterDao().delete()
    }
}
