package com.themovieguide.data.sources.local.repository.toprated

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.TopRatedDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedDBImpl @Inject constructor(
    private var app: AppDatabase,
) : TopRatedDBRepository {
    override fun insertMovie(movie: TopRatedDB) {
        app.topRatedDao().insertMovie(movie)
    }

    override fun insertTopRated(movie: TopRatedDB) {
        app.topRatedDao().insertOnTopRated(
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

    override fun getMovies(): Flow<List<TopRatedDB>> {
        return app.topRatedDao().getMovies()
    }

    override fun getMoviesByTitle(): Flow<List<TopRatedDB>> {
        return app.topRatedDao().getMoviesByTitle()
    }

    override suspend fun getMovieById(movieId: Int): TopRatedDB {
        return app.topRatedDao().getMovieById(movieId)
    }

    override suspend fun deleteMovie(movieId: Int) {
        app.topRatedDao().deleteMovie(movieId)
    }

    override suspend fun delete() {
        app.topRatedDao().delete()
    }
}
