package com.mobile.ngefilm.ui.movielist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ngefilm.ApiClient
import com.mobile.ngefilm.Movie
import com.mobile.ngefilm.MovieAdapter
import com.mobile.ngefilm.MovieDetailActivity
import com.mobile.ngefilm.MovieResponse
import com.mobile.ngefilm.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val movies = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MovieAdapter(
            movies,
            onItemClick = { movie ->
                val intent = Intent(context, MovieDetailActivity::class.java).apply {
                    putExtra("MOVIE_TITLE", movie.title)
                    putExtra("MOVIE_OVERVIEW", movie.overview)
                    putExtra("MOVIE_POSTER", movie.poster_path)
                    putExtra("MOVIE_VIDEO", movie.video_url)
                    putExtra("MOVIE_RATING", movie.vote_average)
                }
                startActivity(intent)
            },
            onFavoriteClick = { movie ->
                movie.isFavorite = !movie.isFavorite
                recyclerView.adapter?.notifyDataSetChanged()
            }
        )

        // Fetch data from API
        fetchMovies()

        return view
    }

    private fun fetchMovies() {
        val apiKey = "f1720574546b51c280b07d1d9392284c" // Ganti dengan API key kamu
        val call = ApiClient.apiService.getPopularMovies(apiKey)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { movieList ->
                        movies.clear()
                        movies.addAll(movieList)
                        recyclerView.adapter?.notifyDataSetChanged()
                        println("Movies fetched: ${movieList.size}") // Debugging
                    }
                } else {
                    println("Response failed: ${response.code()}") // Debugging
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println("API call failed: ${t.message}")
                t.printStackTrace()
            }
        })
    }
}