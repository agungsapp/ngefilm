package com.mobile.ngefilm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("3/search/movie")
    fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MovieResponse>

    @GET("3/movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Call<VideoResponse>
}