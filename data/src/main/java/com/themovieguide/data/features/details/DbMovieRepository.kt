package com.themovieguide.data.features.details

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.mapper.castToMovieDB
import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.domain.features.showing.LocalRepository
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateMoviesDB
import com.themovieguide.domain.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DbMovieRepository @Inject constructor(private val db: AppDatabase) : LocalRepository {
    override fun insertMovie(movie: Movie) {
        val movieDB = movie.castToMovieDB()
        db.movieDao().insertMovie(movieDB)
    }

    override suspend fun getMovies(): StateMoviesDB {
        return try {
            val response = db.movieDao().getMovies()
            StateMoviesDB.OnSuccess(data = response.toDomainFlowList())
        } catch (ex: Exception) {
            StateMoviesDB.OnFailure(error = ex.message ?: EMPTY)
        }
    }
}

fun Flow<List<MovieDB>>.toDomainFlowList(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.id,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.movieKey,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.visited.toString(),
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}
