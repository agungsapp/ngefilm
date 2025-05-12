package com.mobile.ngefilm

import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val title = intent.getStringExtra("MOVIE_TITLE")
        val overview = intent.getStringExtra("MOVIE_OVERVIEW")
        val posterPath = intent.getStringExtra("MOVIE_POSTER")
        val videoUrl = intent.getStringExtra("MOVIE_VIDEO")
        val rating = intent.getFloatExtra("MOVIE_RATING", 0.0f)

        findViewById<TextView>(R.id.detail_title).text = title
        findViewById<TextView>(R.id.detail_overview).text = overview
        findViewById<TextView>(R.id.detail_rating).text = "Rating: $rating/10" // Tambahkan TextView untuk rating
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(findViewById<ImageView>(R.id.detail_poster))

        val webView = findViewById<WebView>(R.id.web_view)
        videoUrl?.let { webView.loadUrl(it) }
    }
}