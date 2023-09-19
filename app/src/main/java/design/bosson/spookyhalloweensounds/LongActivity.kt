package design.bosson.spookyhalloweensounds

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_long.toolbar

class LongActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList: BooleanArray
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
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
    // temporary popup to notiffy users of new restart feature
    private fun showPopup() {
        // Create the AlertDialog with your custom style
        val builder = AlertDialog.Builder(this, R.style.RoundedAlertDialog)
        builder.setMessage(getString(R.string.popup_text_pause))
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()

        // Set up custom animations
        val scaleX = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleX", 0.5f, 1f)
        val scaleY = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleY", 0.5f, 1f)
        val fadeIn = ObjectAnimator.ofFloat(dialog.window!!.decorView, "alpha", 0f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 300 // Set the duration as needed
        animatorSet.playTogether(scaleX, scaleY, fadeIn)
        animatorSet.start()

        // Show the dialog after setting up the animations
        dialog.show()
    }

    private fun initMediaPlayer() {
        val halloween = MediaPlayer.create(this, R.raw.ultra_terror)
        val exorcist = MediaPlayer.create(this, R.raw.haunted_house)
        val shining = MediaPlayer.create(this, R.raw.long_mix)
        val elmstreet = MediaPlayer.create(this, R.raw.space_terror)
        val friday = MediaPlayer.create(this, R.raw.dont_let_in)

        mediaPlayerList = mutableListOf(halloween, exorcist, shining, elmstreet, friday)
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
            findViewById<Button>(R.id.buttonTerrorMix),
            findViewById(R.id.buttonHauntedMix),
            findViewById(R.id.buttonLongMix),
            findViewById(R.id.buttonSpaceTerror),
            findViewById(R.id.buttonDontLetIn)
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
                    this@LongActivity,
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
                    0 -> R.drawable.ic_ultra_terror
                    1 -> R.drawable.ic_haunted_circus
                    2 -> R.drawable.ic_spooky_sounds
                    3 -> R.drawable.ic_space_terrors
                    4 -> R.drawable.ic_key
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
                    this@LongActivity,
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
    // Define the PrefManager class outside onCreate
    class PrefManager(context: Context) {
        private val PREF_NAME = "MyAppPreferences"
        private val KEY_FIRST_TIME_MAIN_ACTIVITY = "firstTimeMainActivity"

        private val pref: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = pref.edit()

        fun setFirstTimeMainActivity(isFirstTime: Boolean) {
            editor.putBoolean(KEY_FIRST_TIME_MAIN_ACTIVITY, isFirstTime)
            editor.apply()
        }

        fun isFirstTimeMainActivity(): Boolean {
            return pref.getBoolean(KEY_FIRST_TIME_MAIN_ACTIVITY, true)
        }
    }
}