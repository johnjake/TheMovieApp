package com.themovieguide.data.sources.remote

import com.themovieguide.data.model.dto.MovieDetails
import com.themovieguide.data.model.meta.CastMeta
import com.themovieguide.data.model.meta.DetailsMeta
import com.themovieguide.data.model.meta.DiscoverMeta
import com.themovieguide.data.model.meta.MovieMeta
import com.themovieguide.data.model.meta.PlayMeta
import com.themovieguide.data.model.meta.ReviewMeta
import com.themovieguide.data.model.meta.television.RatedMeta
import com.themovieguide.data.model.meta.television.SearchMeta
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/550")
    fun movieDetails(
        @Query("api_key") apiKey: String,
    )

    @GET("3/movie/{movieCode}")
    suspend fun movieDetails(
        @Path("movieCode") movieCode: Int,
        @Query("api_key") apiKey: String,
    )

    /** Movie Discover **/

    @GET("3/discover/movie")
    fun discoverMovie(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int,
    ): DiscoverMeta

    @GET("3/movie/now_playing")
    suspend fun inTheater(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
        @Query("region") region: String = "PH",
        @Query("page") pageNumber: Int = 1,
    ): MovieMeta

    @GET("3/movie/{movie_path}")
    suspend fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Path("movie_path") moviePath: Int,
    ): MovieDetails

    @GET("3/movie/{movie_path}")
    suspend fun detailsMovie(
        @Path("movie_path") moviePath: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") append: String = "videos",
    ): DetailsMeta

    @GET("3/movie/top_rated")
    suspend fun inTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
        @Query("region") region: String = "PH",
        @Query("page") pageNumber: Int,
    ): MovieMeta

    @GET("3/movie/top_rated")
    suspend fun topRating(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
        @Query("page") pageNumber: Int,
    ): MovieMeta

    @GET("3/movie/now_playing")
    suspend fun nowShowingLimit(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
    ): MovieMeta

    @GET("3/movie/upcoming")
    suspend fun upComing(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
        @Query("page") pageNumber: Int = 1,
    ): MovieMeta

    @GET("3/movie/upcoming")
    suspend fun upComingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") languages: String = "en-US",
        @Query("page") pageNumber: Int,
    ): MovieMeta

    @GET("3/movie/{movie_id}/release_dates")
    suspend fun movieRelease(
        @Query("api_key") apiKey: String,
        @Query("movie_id") movieId: Int,
    )

    @GET("3/movie/{movie_id}/reviews")
    suspend fun movieReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): ReviewMeta

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): CastMeta

    @GET("3/movie/{movie_id}/videos")
    suspend fun playback(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int,
    ): PlayMeta

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("query") searchKey: String,
        @Query("page") pageNumber: Int,
        @Query("include_adult") adult: Boolean = true,
        @Query("region") region: String = "US",
    ): MovieMeta

    /** tv end point **/
    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1,
    ): RatedMeta

    @GET("/3/search/tv")
    suspend fun getSearchTelevision(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") adults: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1,
    ): SearchMeta
}
