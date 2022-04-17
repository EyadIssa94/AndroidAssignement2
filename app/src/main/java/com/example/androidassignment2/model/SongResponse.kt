package com.example.androidassignment2.model

class SongList: ArrayList<SongResponse>()

data class SongResponse(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: String,
    val previewUrl: String
)

data class apiResponse(
    val resultCount: Int,
    val results: SongList
)

