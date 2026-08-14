package com.example.soundboard

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    private var player: MediaPlayer? = null
    private var currentResourceId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FullScreen mode
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
        supportActionBar?.hide()

        val sounds = arrayOf(
            R.raw.ants_go_marching,
            R.raw.bingo,
            R.raw.doremi,
            R.raw.happy_and_you_know,
            R.raw.jolly_good_fellow,
            R.raw.london_bridge,
            R.raw.old_mac_donald,
            R.raw.six_little_ducks,
            R.raw.twinkle_little_star
        )

        val buttonIds = arrayOf(
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9
        )

        for (i in buttonIds.indices) {
            findViewById<Button>(buttonIds[i]).setOnClickListener {
                playSound(sounds[i])
            }
        }

        findViewById<Button>(R.id.pauseButton).setOnClickListener {
            player?.let {
                if (it.isPlaying)
                    it.pause()
                else
                    it.start()
            }
        }
    }

    private fun playSound(resource: Int) {
        if (currentResourceId == resource) {
            player?.stop()
            player?.release()
            player = null
            currentResourceId = 0
        } else {
            player?.stop()
            player?.release()

            player = MediaPlayer.create(this, resource)
            player?.let {
                it.isLooping = true
                it.start()
                currentResourceId = resource
            }
        }
    }

    override fun onDestroy() {
        player?.release()
        super.onDestroy()
    }
}