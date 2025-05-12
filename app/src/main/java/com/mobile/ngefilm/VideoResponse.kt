package com.mobile.ngefilm

data class VideoResponse(
    val results: List<Video>
)

data class Video(
    val key: String // YouTube video key
)