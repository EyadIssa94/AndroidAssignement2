package com.example.androidassignment2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment2.R
import com.example.androidassignment2.adapters.SongTracksAdapter
import com.example.androidassignment2.model.apiResponse
import com.example.androidassignment2.model.remote.RockTracksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RockFragment : Fragment() {

    private lateinit var rockSongsList: RecyclerView
    private lateinit var adapter: SongTracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view =  inflater.inflate(
            R.layout.fragment_rock,
            container,
            false)
        initViews(view)
        getRockList()
        return view
    }

    private fun getRockList() {
        RockTracksService.initRetrofit().getRockTracks()
            .enqueue(
                object : Callback<apiResponse> {
                    override fun onResponse(
                        call: Call<apiResponse>,
                        response: Response<apiResponse>) {
                        if (response.isSuccessful){
                            updateAdapter(response.body())
                        }else{
                            showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<apiResponse>, t: Throwable) {
                        showError(t.message ?: "Unknown error")
                    }
                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: apiResponse?) {
        body?.let {
            adapter = SongTracksAdapter(it)
            rockSongsList.adapter = adapter
        }
    }

    private fun initViews(view: View) {
        rockSongsList = view.findViewById(R.id.rock_songs_list)
        rockSongsList.layoutManager = LinearLayoutManager(context)
    }
}