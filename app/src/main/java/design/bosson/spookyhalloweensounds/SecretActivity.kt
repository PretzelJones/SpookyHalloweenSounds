package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
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

    private var currentPlayingButton: View? = null // Track the currently pressed button associated with media.
    private val mediaPlayerQueue = LinkedList<MediaPlayer>() // Queue to manage MediaPlayer instances.
    private var currentPlayingMediaPlayer: MediaPlayer? = null // Reference to the currently playing MediaPlayer.
    private val mediaPlayerMap = mutableMapOf<View, MediaPlayer>()

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
    private fun playSound(soundId: Int, button: View) {
        try {
            // Check if a MediaPlayer is associated with this button
            val existingMediaPlayer = mediaPlayerMap[button]

            if (existingMediaPlayer != null) {
                if (existingMediaPlayer.isPlaying) {
                    // If the MediaPlayer associated with this button is playing, pause it
                    existingMediaPlayer.pause()
                } else {
                    // If the MediaPlayer associated with this button is paused, resume playback
                    existingMediaPlayer.start()
                }
            } else {
                // Create a new MediaPlayer instance for the current sound
                val mediaPlayer = MediaPlayer.create(this, soundId)

                // Set completion listener to release the MediaPlayer when sound finishes
                mediaPlayer.setOnCompletionListener {
                    it.release()
                    mediaPlayerMap.remove(button)

                    // Change the button color back to colorButton when sound finishes
                    resetButtonColor(button)
                }

                // Add the new MediaPlayer to the map and start playing the current sound
                mediaPlayerMap[button] = mediaPlayer

                // Change the button color to colorButtonPressed
                button.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@SecretActivity,
                        R.color.colorButtonPressed
                    )
                )

                mediaPlayer.start()

                // Add a long-press listener to restart the audio
                button.setOnLongClickListener {
                    mediaPlayer.seekTo(0) // Restart the audio from the beginning
                    true // Consume the long-press event
                }
            }
        } catch (e: Resources.NotFoundException) {
            // Handle the case where the resource with the given soundId is not found
            e.printStackTrace()
        } catch (e: Exception) {
            // Handle other exceptions
            e.printStackTrace()
        }
    }
    private fun resetButtonColor(button: View) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this@SecretActivity,
                R.color.colorButton
            )
        )
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerQueue.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayerQueue.clear()
    }
    override fun onPause() {
        super.onPause()
        if (currentPlayingMediaPlayer != null) {
            currentPlayingMediaPlayer!!.stop()
            currentPlayingMediaPlayer!!.release()
            currentPlayingMediaPlayer = null
        }
    }
    override fun onResume() {
        super.onResume()
        currentPlayingButton?.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.colorButton)
        )
        if (currentPlayingMediaPlayer != null && !currentPlayingMediaPlayer!!.isPlaying) {
            currentPlayingMediaPlayer!!.start()
        }
    }
    override fun onStop() {
        super.onStop()

        // Release all media players in the mediaPlayerMap
        mediaPlayerMap.values.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayerMap.clear()

        // Reset the button colors for all buttons associated with media players
        mediaPlayerMap.keys.forEach { button ->
            resetButtonColor(button)
        }

        // Release the currentPlayingMediaPlayer if it exists
        currentPlayingMediaPlayer?.release()
        currentPlayingMediaPlayer = null
    }
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