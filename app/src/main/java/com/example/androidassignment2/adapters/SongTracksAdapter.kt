package com.example.androidassignment2.adapters

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaParser
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment2.R
import com.example.androidassignment2.model.SongResponse
import com.example.androidassignment2.model.apiResponse
import com.squareup.picasso.Picasso
import java.io.IOException

var songPlayer: MediaPlayer? = null

class SongTracksAdapter(private val dataSet: apiResponse):
    RecyclerView.Adapter<SongTracksAdapter.SongsViewHolder>() {

    class SongsViewHolder (private val view: View): RecyclerView.ViewHolder(view) {
        private val songPoster: ImageView = view.findViewById(R.id.iv_song_poster)
        private val collectionName: TextView = view.findViewById(R.id.tv_collection_name)
        private val artistName: TextView = view.findViewById(R.id.tv_artist_name)
        private val trackPrice: TextView = view.findViewById(R.id.tv_track_price)
        private val songCardView: View = view.findViewById(R.id.card_view)

        fun onBind(dataItem: SongResponse){
            Picasso.get().load(dataItem.artworkUrl60).resizeDimen(R.dimen.image_size, R.dimen.image_size).into(songPoster)
            //Picasso.get().load(dataItem.artworkUrl60).fit().centerCrop().into(songPoster)
            //Picasso.get().load(dataItem.artworkUrl60).resize(200,200).into(songPoster)
            collectionName.text = dataItem.collectionName
            artistName.text = dataItem.artistName
            trackPrice.text = dataItem.trackPrice + " USD"

            songCardView.setOnClickListener {
                val url = dataItem.previewUrl
                if (songPlayer?.isPlaying == true){
                    songPlayer?.stop()
                }else{
                    try {
                        songPlayer = MediaPlayer().apply {
                            setAudioAttributes(
                                AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                            )
                            setDataSource(url)
                            prepare() // might take long! (for buffering, etc)
                            start()
                        }
                    }catch (exception: IOException){
                        songPlayer?.release()
                        songPlayer = null
                    }
                }
            }
        }

//        private fun playSong(previewUrl: String) {
//            songPlayer!!.setDataSource(previewUrl)
//            songPlayer!!.prepare()
//            songPlayer!!.start()
//        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        return SongsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.songs_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.onBind(dataSet.results[position])
    }

    override fun getItemCount(): Int {
        return dataSet.results.size
    }

}