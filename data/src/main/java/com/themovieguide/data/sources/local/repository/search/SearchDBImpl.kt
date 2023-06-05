package com.themovieguide.data.sources.local.repository.search

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.SearchDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDBImpl @Inject constructor(
    private var app: AppDatabase,
) : SearchDBRepository {
    override fun insertMovie(movie: SearchDB) {
        app.searchDao().insertMovie(movie)
    }

    override fun insertSearch(movie: SearchDB) {
        app.searchDao().insertOnSearch(
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

    override fun getMovies(title: String): Flow<List<SearchDB>> {
        return app.searchDao().getMovies(title)
    }

    override fun getMoviesByTitle(): Flow<List<SearchDB>> {
        return app.searchDao().getMoviesByTitle()
    }

    override suspend fun getMovieById(movieId: Int): SearchDB {
        return app.searchDao().getMovieById(movieId)
    }

    override suspend fun deleteMovie(movieId: Int) {
        app.topRatedDao().deleteMovie(movieId)
    }

    override suspend fun delete() {
        app.topRatedDao().delete()
    }
}
