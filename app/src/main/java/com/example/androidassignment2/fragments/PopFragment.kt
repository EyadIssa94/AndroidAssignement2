package com.example.androidassignment2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidassignment2.R
import com.example.androidassignment2.adapters.SongTracksAdapter
import com.example.androidassignment2.model.ApiResponse
import com.example.androidassignment2.model.remote.PopTracksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopFragment : Fragment() {

    private lateinit var popSongsList: RecyclerView
    private lateinit var adapter: SongTracksAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        refreshLayout()

        return view
    }

    private fun refreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Page Refreshed", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getPopList() {
        PopTracksService.initRetrofit().getPopTracks()
            .enqueue(
                object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful){
                            updateAdapter(response.body())
                        }else{
                            showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        showError(t.message ?: "Unknown Message")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: ApiResponse?) {
        body?.let {
            adapter = SongTracksAdapter(it)
            popSongsList.adapter = adapter
        }
    }

    private fun initViews(view: View) {
        popSongsList = view.findViewById(R.id.pop_songs_list)
        popSongsList.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
    }
}