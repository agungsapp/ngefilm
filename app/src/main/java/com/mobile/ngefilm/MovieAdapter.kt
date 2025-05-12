package com.mobile.ngefilm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit,
    private val onFavoriteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val poster: ImageView = itemView.findViewById(R.id.movie_poster)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.poster)

        // Set the correct icon for favorite button based on the movie's favorite status
        val favoriteIcon = if (movie.isFavorite) R.drawable.favorite else R.drawable.ic_favorite_border
        holder.favoriteButton.setImageResource(favoriteIcon)

        holder.favoriteButton.setImageResource(favoriteIcon)

        holder.itemView.setOnClickListener { onItemClick(movie) }
        holder.favoriteButton.setOnClickListener {
            val animation = AlphaAnimation(0.3f, 1.0f)
            animation.duration = 200
            it.startAnimation(animation)
            onFavoriteClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
