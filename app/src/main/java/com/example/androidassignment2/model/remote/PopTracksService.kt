package com.example.androidassignment2.model.remote

import com.example.androidassignment2.common.BASE_URL
import com.example.androidassignment2.common.POP_END_POINT
import com.example.androidassignment2.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PopTracksService {
    @GET(POP_END_POINT)
    fun getPopTracks(): Call<ApiResponse>

    companion object {
        fun initRetrofit(): PopTracksService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PopTracksService::class.java)
        }
    }
}