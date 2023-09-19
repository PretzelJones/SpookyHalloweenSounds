package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_secret.toolbar

class SecretActivity : AppCompatActivity() {

    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList: BooleanArray
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Initialize MediaPlayer instances and isPlayingList
        initMediaPlayer()

        // Set click listeners for buttons
        setButtonClickListeners()
    }

    private fun initMediaPlayer() {
        val theGhostSong = MediaPlayer.create(this, R.raw.the_ghost_song)
        val theOldTape = MediaPlayer.create(this, R.raw.the_old_tape)
        val chillingCries = MediaPlayer.create(this, R.raw.chilling_cries)
        val criesFromHell = MediaPlayer.create(this, R.raw.cries_from_hell)

        mediaPlayerList = mutableListOf(theGhostSong, theOldTape, chillingCries, criesFromHell)
        isPlayingList = BooleanArray(mediaPlayerList.size) { false }

        // Set OnCompletionListener for each MediaPlayer to loop indefinitely
        mediaPlayerList.forEach { mediaPlayer ->
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.start() // Start playback again when it completes
                mediaPlayer.isLooping = true // Set looping to true
            }
        }
    }
    private fun setButtonClickListeners() {
        val buttons = listOf(
            findViewById<Button>(R.id.buttonTheGhostSong),
            findViewById(R.id.buttonTheOldTape),
            findViewById(R.id.buttonChillingCries),
            findViewById(R.id.buttonCriesFromHell)
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val mediaPlayer = mediaPlayerList[index]
                val isPlaying = isPlayingList[index]

                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlayingList[index] = false
                    val playDrawable = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_play,
                        null
                    )
                    button.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        playDrawable,
                        null,
                        null
                    )
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            R.color.colorTitle
                        )
                    )
                } else {
                    if (!isPlaying) {
                        mediaPlayer.start()
                        isPlayingList[index] = true
                        val pauseDrawable = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_pause,
                            null
                        )
                        button.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            pauseDrawable,
                            null,
                            null
                        )
                        button.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this,
                                R.color.colorTitleLight
                            )
                        )
                    }
                }
                // Apply the button animation here
                val animation = AnimationUtils.loadAnimation(
                    this@SecretActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)
            }

            button.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[index]
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList[index] = false
                mediaPlayer.pause()

                val drawableResId = when (index) {
                    0 -> R.drawable.ic_ghost_song
                    1 -> R.drawable.ic_old_tape
                    2 -> R.drawable.ic_dispair
                    3 -> R.drawable.ic_cries_hell
                    else -> R.drawable.ic_play // Default
                }
                val drawable = ResourcesCompat.getDrawable(
                    resources,
                    drawableResId,
                    null
                )
                button.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    drawable,
                    null,
                    null
                )
                val animation = AnimationUtils.loadAnimation(
                    this@SecretActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)

                true // Return true to indicate that the long click event is consumed
            }
        }
    }
    // Release media player when switching between activities
    override fun onStop() {
        super.onStop()
        mediaPlayerList.forEach {
            it.release()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, DeveloperActivity::class.java)
                this.startActivity(intent)
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
        }
        return true
    }
}
