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
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import design.bosson.spookyhalloweensounds.databinding.ActivityLongBinding
import java.util.LinkedList

class LongActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLongBinding

    private var currentPlayingButton: View? = null // Track the currently pressed button associated with media.
    private val mediaPlayerQueue = LinkedList<MediaPlayer>() // Queue to manage MediaPlayer instances.
    private var currentPlayingMediaPlayer: MediaPlayer? = null // Reference to the currently playing MediaPlayer.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.colorAccent)
        // Ensure icons are white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = ActivityLongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.menuHamburger.setOnClickListener { view ->
            showPopupMenu(view)
        }

        // Use the binding to access views in the layout
        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        // Initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        // Set up button click listeners
        binding.bTerrorMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.ultra_terror, it)
        }
        binding.bHauntedMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_house, it)
        }
        binding.bLongMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.long_mix, it)
        }
        binding.bSpaceTerror.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.space_terror, it)
        }
        binding.bDontLetIn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.dont_let_in, it)
        }
    }

    private fun playSound(soundId: Int, button: View) {
        try {
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

            // Add the new MediaPlayer to the queue
            mediaPlayerQueue.add(mediaPlayer)

            // Check if the queue size exceeds the limit (e.g., 10)
            if (mediaPlayerQueue.size > 10) {
                // Release the oldest MediaPlayer
                val oldestMediaPlayer = mediaPlayerQueue.poll()
                oldestMediaPlayer?.release()
            }

            // Start playing the current sound
            mediaPlayer.start()

            // Set the current media player and button to the ones just created
            currentPlayingMediaPlayer = mediaPlayer
            currentPlayingButton = button
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle any exceptions here, e.g., log an error message or show a toast
        }
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

    private fun openSecretActivity() {
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }

        popupMenu.show()
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
