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
import kotlinx.android.synthetic.main.activity_long.toolbar

class MovieActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList: BooleanArray
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Initialize firebase analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        // Initialize MediaPlayer instances and isPlayingList
        initMediaPlayer()
        // Set click listeners for buttons
        setButtonClickListeners()
        // Initialize prefManager
        prefManager = PrefManager(this)
        // Check if it's the first time
        if (prefManager.isFirstTimeMainActivity()) {
            showPopup()
            prefManager.setFirstTimeMainActivity(false) // Mark as visited
        }
    }
    private fun showPopup() {
        PopupUtils.showPopup(this)
    }
    private fun initMediaPlayer() {
        val halloween = MediaPlayer.create(this, R.raw.halloween)
        val exorcist = MediaPlayer.create(this, R.raw.exorcist)
        val shining = MediaPlayer.create(this, R.raw.shining)
        val elmstreet = MediaPlayer.create(this, R.raw.elm_street)
        val friday = MediaPlayer.create(this, R.raw.vorhees)
        val amityville = MediaPlayer.create(this, R.raw.amityville)

        mediaPlayerList = mutableListOf(halloween, exorcist, shining, elmstreet, friday, amityville)
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
            findViewById<Button>(R.id.buttonHalloween),
            findViewById(R.id.buttonExorcist),
            findViewById(R.id.buttonShining),
            findViewById(R.id.buttonElmStreet),
            findViewById(R.id.buttonFriday),
            findViewById(R.id.buttonAmityville)
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
                    this@MovieActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)
            }

            button.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[index]
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList[index] = false
                mediaPlayer.pause()

                // Change button background color
                button.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTitle
                    )
                )

                val drawableResId = when (index) {
                    0 -> R.drawable.ic_halloween
                    1 -> R.drawable.ic_cross
                    2 -> R.drawable.ic_axe
                    3 -> R.drawable.ic_krueger
                    4 -> R.drawable.ic_vorhees
                    5 -> R.drawable.ic_amityville
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
                    this@MovieActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)

                true // Return true to indicate that the long click event is consumed
            }
        }
    }
    override fun onPause() {
        super.onPause()
        AudioControl.pauseAudio(mediaPlayerList)
    }
    override fun onResume() {
        super.onResume()
        AudioControl.resumeAudio(mediaPlayerList)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, DeveloperActivity::class.java)
                startActivity(intent)
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
