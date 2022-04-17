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
import com.example.androidassignment2.model.remote.PopTracksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopFragment : Fragment() {

    private lateinit var popSongsList: RecyclerView
    private lateinit var adapter: SongTracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_pop,
            container,
            false)
        initViews(view)
        getPopList()
        return view
    }

    private fun getPopList() {
        PopTracksService.initRetrofit().getPopTracks()
            .enqueue(
                object : Callback<apiResponse> {
                    override fun onResponse(
                        call: Call<apiResponse>,
                        response: Response<apiResponse>
                    ) {
                        if (response.isSuccessful){
                            updateAdapter(response.body())
                        }else{
                            showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<apiResponse>, t: Throwable) {
                        showError(t.message ?: "Unknown Message")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: apiResponse?) {
        body?.let {
            adapter = SongTracksAdapter(it)
            popSongsList.adapter = adapter
        }
    }

    private fun initViews(view: View) {
        popSongsList = view.findViewById(R.id.pop_songs_list)
        popSongsList.layoutManager = LinearLayoutManager(context)
    }
}