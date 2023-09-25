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
import kotlinx.android.synthetic.main.content_long.bDontLetIn
import kotlinx.android.synthetic.main.content_long.bHauntedMix
import kotlinx.android.synthetic.main.content_long.bLongMix
import kotlinx.android.synthetic.main.content_long.bSpaceTerror
import kotlinx.android.synthetic.main.content_long.bTerrorMix
import java.util.LinkedList

class LongActivity : AppCompatActivity() {

    // Initialize the MediaPlayer
    //private var mp: MediaPlayer? = null
    private var currentPlayingButton: View? = null // Track the currently pressed button associated with media.
    private val mediaPlayerQueue = LinkedList<MediaPlayer>() // Queue to manage MediaPlayer instances.
    private var currentPlayingMediaPlayer: MediaPlayer? = null // Reference to the currently playing MediaPlayer.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display
        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        bTerrorMix.setOnClickListener {
            bTerrorMix.startAnimation(buttonAnimation)
            playSound(R.raw.ultra_terror, bTerrorMix)
        }
        bHauntedMix.setOnClickListener {
            bHauntedMix.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_house, bHauntedMix)
        }
        bLongMix.setOnClickListener {
            bLongMix.startAnimation(buttonAnimation)
            playSound(R.raw.long_mix, bLongMix)
        }
        bSpaceTerror.setOnClickListener {
            bSpaceTerror.startAnimation(buttonAnimation)
            playSound(R.raw.space_terror, bSpaceTerror)
        }
        bDontLetIn.setOnClickListener {
            bDontLetIn.startAnimation(buttonAnimation)
            playSound(R.raw.dont_let_in, bDontLetIn)
        }
    }
        /*
        bTerrorMix.setOnClickListener {
            bTerrorMix.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@LongActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bTerrorMix

            // Change the background color of the currently playing button to colorButtonPressed
            bTerrorMix.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@LongActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bHauntedMix.setOnClickListener {
            bHauntedMix.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@LongActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bHauntedMix

            // Change the background color of the currently playing button to colorButtonPressed
            bHauntedMix.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@LongActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bLongMix.setOnClickListener {
            bLongMix.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@LongActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bLongMix

            // Change the background color of the currently playing button to colorButtonPressed
            bLongMix.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@LongActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bSpaceTerror.setOnClickListener {
            bSpaceTerror.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@LongActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bSpaceTerror

            // Change the background color of the currently playing button to colorButtonPressed
            bSpaceTerror.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@LongActivity,
                    R.color.colorButtonPressed
                )
            )
        }

        bDontLetIn.setOnClickListener {
            bDontLetIn.startAnimation(buttonAnimation)

            // Check if a media player is currently playing
            if (currentPlayingButton != null) {
                // Change the background color of the previously playing button back to colorTitle
                currentPlayingButton!!.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@LongActivity,
                        R.color.colorTitle
                    )
                )
            }

            // Start playing the media player
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.dont_let_in)
            mPlay()

            // Update the currently playing button reference
            currentPlayingButton = bDontLetIn

            // Change the background color of the currently playing button to colorButtonPressed
            bDontLetIn.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@LongActivity,
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
                        this@LongActivity,
                        R.color.colorButton
                    )
                )
            }
        }
        // Change the button color to colorButtonPressed
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this@LongActivity,
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
                    this@LongActivity,
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
