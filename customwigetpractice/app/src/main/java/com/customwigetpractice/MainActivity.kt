package com.customwigetpractice

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var mediaPlayer:MediaPlayer?=null //0~1
    var vol  = 0.0f
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initlayout()
    }

    override fun onResume() {
        super.onResume()
        if(flag)
            mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    private fun initlayout() {
        val imageView = findViewById<VolumeControlView>(R.id.imageView)
        imageView.setVolumeListener(object:VolumeControlView.VolumeListener{
            override fun onChanged(angle: Float) {
            // angle -> volume
                vol = if(angle>0){
                    angle/360
                }else{
                    (360+angle)/360
                }
                mediaPlayer?.setVolume(vol,vol)
            }
        })

        val playBtn = findViewById<Button>(R.id.playBtn)
        playBtn.setOnClickListener {
            if(mediaPlayer==null){
                mediaPlayer = MediaPlayer.create(this,R.raw.music)
                mediaPlayer?.setVolume(vol,vol)
            }
            mediaPlayer?.start()
            flag = true
        }

        val pauseBtn = findViewById<Button>(R.id.pauseBtn)
        pauseBtn.setOnClickListener {
            mediaPlayer?.pause()
            flag = false
        }

        val stopBtn = findViewById<Button>(R.id.stopBtn)
        stopBtn.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release() //resouce release
            mediaPlayer = null
            flag = false
        }
    }
}