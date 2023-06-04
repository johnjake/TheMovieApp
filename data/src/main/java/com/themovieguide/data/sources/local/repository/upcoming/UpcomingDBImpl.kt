package com.themovieguide.data.sources.local.repository.upcoming

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.UpcomingDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingDBImpl @Inject constructor(
    private var app: AppDatabase,
) : UpcomingDBRepository {
    override fun insertMovie(movie: UpcomingDB) {
        app.upcomingDao().insertMovie(movie)
    }

    override fun insertUpcoming(movie: UpcomingDB) {
        app.upcomingDao().insertUpcoming(
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

    override fun getMovies(): Flow<List<UpcomingDB>> {
        return app.upcomingDao().getMovies()
    }

    override fun getMoviesByTitle(): Flow<List<UpcomingDB>> {
        return app.upcomingDao().getMoviesByTitle()
    }

    override suspend fun getMovieById(movieId: Int): UpcomingDB {
        return app.upcomingDao().getMovieById(movieId)
    }

    override suspend fun deleteMovie(movieId: Int) {
        app.upcomingDao().deleteMovie(movieId)
    }

    override suspend fun delete() {
        app.upcomingDao().delete()
    }
}