package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_scrolling.textCountdown
import java.util.Calendar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var timer: CountDownTimer
    private lateinit var mediaPlayerList: MutableList<MediaPlayer> //new
    private lateinit var isPlayingList: BooleanArray //new
    private lateinit var buttons: List<Button> //new

    // Initializes the "halloweenDate" variable with a specific date and time.
    private var halloweenDate = Calendar.getInstance().apply {
        set(Calendar.MONTH, Calendar.NOVEMBER)     // Set the month to November
        set(Calendar.DAY_OF_MONTH, 1)             // Set the day of the month to 1
        set(Calendar.HOUR_OF_DAY, 0)              // Set the hour of the day to 0 (midnight)
        set(Calendar.MINUTE, 0)                   // Set the minute to 0
        set(Calendar.SECOND, 0)                   // Set the second to 0
    }.timeInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // Initialize Firebase instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)
        // Initialize the buttons list using ButtonListProvider
        buttons = ButtonListProvider.buttonIds.map { findViewById<Button>(it) }
        // Initialize MediaPlayer instances and isPlayingList
        initMediaPlayer()
        // Set click listeners for buttons
        setButtonClickListeners()
        // Prompt users to rate app via in-app review
        showFeedbackDialog()
        // Update the display with the remaining days until Halloween
        updateCountdown()
        // Retrieves the view with the ID overlayImageView (ic_key) for secretactivity
        val overlayImageView = findViewById<View>(R.id.overlayImageView)
        // Create and start a new countdown timer based on the time remaining until Halloween.
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown() // Continuously updates the countdown display as time elapses
            }
            override fun onFinish() {
                resetTimer() // When the timer finishes, reset it for the next year's Halloween
            }
        }
        timer.start() // Start the countdown timer

        // Set an OnClickListener to handle the click event for SecretActivity
        overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        val bLoopingMix: Button = findViewById(R.id.buttonLoopingMix)
        bLoopingMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            val intent = Intent(this, LongActivity::class.java)
            startActivity(intent)
        }
        val bMovieThemes: Button = findViewById(R.id.buttonMovieThemes)
        bMovieThemes.setOnClickListener {
            it.startAnimation(buttonAnimation)
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initMediaPlayer() {
        mediaPlayerList = RawAudioProvider.audioResources.map { resourceId ->
            MediaPlayer.create(this, resourceId)
        }.toMutableList()

        isPlayingList = BooleanArray(mediaPlayerList.size) { false }

        // Set OnCompletionListener for each MediaPlayer
        mediaPlayerList.forEachIndexed { index, mediaPlayer ->
            mediaPlayer.setOnCompletionListener {
                val button = buttons[index] // Access the button using the stored list
                runOnUiThread {
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.colorTitle)
                    )
                }
                val animation = AnimationUtils.loadAnimation(
                    this@ScrollingActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)
                isPlayingList[index] = false
            }
        }
    }
    private fun setButtonClickListeners() {
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val mediaPlayer = mediaPlayerList[index]
                val isPlaying = isPlayingList[index]

                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlayingList[index] = false
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
                    this@ScrollingActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)
            }
            button.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[index]
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList[index] = false
                mediaPlayer.pause()

                val animation = AnimationUtils.loadAnimation(
                    this@ScrollingActivity,
                    R.anim.button_animation
                )
                button.startAnimation(animation)

                true // Return true to indicate that the long click event is consumed
            }
        }
    }
    // Function to show feedback dialog
    private fun showFeedbackDialog() {
        val reviewManager = ReviewManagerFactory.create(applicationContext)
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) {
                reviewManager.launchReviewFlow(this, it.result)
            }
        }
    }
    // Launch hidden sounds activity on hidden icon press
    private fun openSecretActivity() {
        // Open the SecretActivity here
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }
    // update countdown timer - do not delete
    private fun updateCountdown() {
        val currentDate = Calendar.getInstance().timeInMillis
        val remainingDays = ((halloweenDate - currentDate) / (1000 * 60 * 60 * 24)).toInt()
        textCountdown.text = if (remainingDays > 0) {
            "$remainingDays days till Halloween"
        } else {
            "Happy Halloween"
        }
        if (remainingDays == 1) {
            textCountdown.text = getString(R.string.tomorrow)
        }
    }
    // Resets the countdown timer for the next year's Halloween
    private fun resetTimer() {
        // Calculate the date for next year's Halloween
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val nextYear = currentYear + 1
        halloweenDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, nextYear)
            set(Calendar.MONTH, Calendar.NOVEMBER)
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis

        // Create a new countdown timer with the updated date
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown()
            }

            override fun onFinish() {
                resetTimer()
            }
        }

        // Start the countdown timer
        timer.start()
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
