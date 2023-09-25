package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_long.toolbar
import kotlinx.android.synthetic.main.content_movie.bAmityville
import kotlinx.android.synthetic.main.content_movie.bElmStreet
import kotlinx.android.synthetic.main.content_movie.bExorcist
import kotlinx.android.synthetic.main.content_movie.bFriday
import kotlinx.android.synthetic.main.content_movie.bHalloween
import kotlinx.android.synthetic.main.content_movie.bShining
import java.util.LinkedList

class MovieActivity : AppCompatActivity() {

    //private var mp: MediaPlayer? = null // Define a variable to keep track of the currently playing button
    private var currentPlayingButton: View? = null // Track the currently pressed button associated with media.
    private val mediaPlayerQueue = LinkedList<MediaPlayer>() // Queue to manage MediaPlayer instances.
    private var currentPlayingMediaPlayer: MediaPlayer? = null // Reference to the currently playing MediaPlayer.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        bHalloween.setOnClickListener {
            bHalloween.startAnimation(buttonAnimation)
            playSound(R.raw.halloween, bHalloween)
        }
        bExorcist.setOnClickListener {
            bExorcist.startAnimation(buttonAnimation)
            playSound(R.raw.exorcist, bExorcist)
        }
        bShining.setOnClickListener {
            bShining.startAnimation(buttonAnimation)
            playSound(R.raw.shining, bShining)
        }
        bElmStreet.setOnClickListener {
            bElmStreet.startAnimation(buttonAnimation)
            playSound(R.raw.elm_street, bElmStreet)
        }
        bFriday.setOnClickListener {
            bFriday.startAnimation(buttonAnimation)
            playSound(R.raw.vorhees, bFriday)
        }
        bAmityville.setOnClickListener {
            bAmityville.startAnimation(buttonAnimation)
            playSound(R.raw.amityville, bAmityville)
        }
    }

        /*
        bHalloween.setOnClickListener {
            bHalloween.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.halloween)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bHalloween

            // Change the background color of the currently playing button to colorButtonPressed
            bHalloween.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bExorcist.setOnClickListener {
            bExorcist.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.exorcist)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bExorcist

            // Change the background color of the currently playing button to colorButtonPressed
            bExorcist.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bShining.setOnClickListener {
            bShining.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorButton
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorButton
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.shining)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bShining

            // Change the background color of the currently playing button to colorButtonPressed
            bShining.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bElmStreet.setOnClickListener {
            bElmStreet.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorButton
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorButton
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.elm_street)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bElmStreet

            // Change the background color of the currently playing button to colorButtonPressed
            bElmStreet.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bFriday.setOnClickListener {
            bFriday.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorButton
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorButton
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.vorhees)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bFriday

            // Change the background color of the currently playing button to colorButtonPressed
            bFriday.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bAmityville.setOnClickListener {
            bAmityville.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorButton
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorButton
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.amityville)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bAmityville

            // Change the background color of the currently playing button to colorButtonPressed
            bAmityville.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButtonPressed
                )
            )
        }
    }  */ //end old code

    private fun playSound(soundId: Int, button: View) {
        // Check if the current media player is already playing and associated with a button
        if (currentPlayingMediaPlayer != null && currentPlayingButton != null) {
            // If the same button is tapped again, pause or resume playback
            if (currentPlayingButton == button) {
                if (currentPlayingMediaPlayer!!.isPlaying) {
                    currentPlayingMediaPlayer!!.pause()
                } else {
                    currentPlayingMediaPlayer!!.start()
                }
                return
            } else {
                // If a different button is tapped, release the current media player
                currentPlayingMediaPlayer!!.release()
                currentPlayingMediaPlayer = null
                // Change the previous button's color back to colorButton
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@MovieActivity,
                        R.color.colorButton
                    )
                )
            }
        }
        // Change the button color to colorButtonPressed
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this@MovieActivity,
                R.color.colorButtonPressed
            )
        )
        // Create a new MediaPlayer instance for the current sound
        val mediaPlayer = MediaPlayer.create(this, soundId)
        // Set completion listener to release the MediaPlayer when sound finishes
        mediaPlayer.setOnCompletionListener {
            it.release()
            mediaPlayerQueue.remove(it)
            // Change the button color back to colorButton when sound finishes
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@MovieActivity,
                    R.color.colorButton
                )
            )
        }
        // Set looping to true to repeat the sound
        mediaPlayer.isLooping = true
        // Add the new MediaPlayer to the queue
        mediaPlayerQueue.add(mediaPlayer)
        // Start playing the current sound
        mediaPlayer.start()
        // Set the current media player and button to the ones just created
        currentPlayingMediaPlayer = mediaPlayer
        currentPlayingButton = button
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerQueue.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayerQueue.clear()
    }
    private fun releaseAllMediaPlayers() {
        mediaPlayerQueue.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayerQueue.clear()
    }

    override fun onPause() {
        super.onPause()
        releaseAllMediaPlayers()
    }
    /*
    private fun mPlay () {
        mp?.start()
        mp?.isLooping = true
        mp?.setScreenOnWhilePlaying(true)
    }
    private fun stopPlaying() {
        // If media player is not null and is playing, then stop and release it
        mp?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mp = null
    }
    override fun onStop() {
        super.onStop()
        stopPlaying()
    }
    */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            val intent = Intent(this, DeveloperActivity::class.java)
            this.startActivity(intent)

        } else if (id == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
            startActivity(Intent.createChooser(sharingIntent, "Share via"))

        } /*else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }*/

        return true
    }
}

