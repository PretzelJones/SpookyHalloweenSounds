package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
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
import kotlinx.android.synthetic.main.content_movie.bHalloween

class LongActivity : AppCompatActivity() {

    // Initialize the MediaPlayer
    private var mp: MediaPlayer? = null
    // Define a variable to keep track of the currently playing button
    private var currentPlayingButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

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
        /*
        //sound managers
        bTerrorMix.setOnClickListener {
            bTerrorMix.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
            mPlay()
        }

        bHauntedMix.setOnClickListener {
            bHauntedMix.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
            mPlay()
        }

        bLongMix.setOnClickListener {
            bLongMix.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
            mPlay()
        }

        bSpaceTerror.setOnClickListener {
            bSpaceTerror.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
            mPlay()
        }

        bDontLetIn.setOnClickListener {
            bDontLetIn.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@LongActivity, R.raw.dont_let_in)
            mPlay()

        }
        */
    }
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
