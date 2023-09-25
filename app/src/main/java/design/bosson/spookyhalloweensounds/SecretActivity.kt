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
import kotlinx.android.synthetic.main.activity_secret.toolbar
import kotlinx.android.synthetic.main.content_secret.bChillingCries
import kotlinx.android.synthetic.main.content_secret.bCriesFromHell
import kotlinx.android.synthetic.main.content_secret.bTheGhostSong
import kotlinx.android.synthetic.main.content_secret.bTheOldTape
import java.util.LinkedList

class SecretActivity : AppCompatActivity() {

    // Initialize the MediaPlayer
    //private var mp: MediaPlayer? = null
    private var currentPlayingButton: View? = null // Track the currently pressed button associated with media.
    private val mediaPlayerQueue = LinkedList<MediaPlayer>() // Queue to manage MediaPlayer instances.
    private var currentPlayingMediaPlayer: MediaPlayer? = null // Reference to the currently playing MediaPlayer.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        bTheGhostSong.setOnClickListener {
            bTheGhostSong.startAnimation(buttonAnimation)
            playSound(R.raw.the_ghost_song, bTheGhostSong)
        }
        bTheOldTape.setOnClickListener {
            bTheOldTape.startAnimation(buttonAnimation)
            playSound(R.raw.the_old_tape, bTheOldTape)
        }
        bChillingCries.setOnClickListener {
            bChillingCries.startAnimation(buttonAnimation)
            playSound(R.raw.chilling_cries, bChillingCries)
        }
        bCriesFromHell.setOnClickListener {
            bCriesFromHell.startAnimation(buttonAnimation)
            playSound(R.raw.cries_from_hell, bCriesFromHell)
        }
    }
        /*
        bTheGhostSong.setOnClickListener {
            bTheGhostSong.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@SecretActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.the_ghost_song)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bTheGhostSong

            // Change the background color of the currently playing button to colorButtonPressed
            bTheGhostSong.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@SecretActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bTheOldTape.setOnClickListener {
            bTheOldTape.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@SecretActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.the_old_tape)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bTheOldTape

            // Change the background color of the currently playing button to colorButtonPressed
            bTheOldTape.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@SecretActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bChillingCries.setOnClickListener {
            bChillingCries.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@SecretActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.chilling_cries)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bChillingCries

            // Change the background color of the currently playing button to colorButtonPressed
            bChillingCries.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@SecretActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bCriesFromHell.setOnClickListener {
            bCriesFromHell.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@SecretActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.cries_from_hell)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bCriesFromHell

            // Change the background color of the currently playing button to colorButtonPressed
            bCriesFromHell.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@SecretActivity,
                    R.color.colorButtonPressed
                )
            )
        }
    }*/ //end old code
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
                        this@SecretActivity,
                        R.color.colorButton
                    )
                )
            }
        }
        // Change the button color to colorButtonPressed
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this@SecretActivity,
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
                    this@SecretActivity,
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
    } */ //end of old code
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