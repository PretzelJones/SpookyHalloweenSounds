package design.bosson.spookyhalloweensounds
import android.content.Intent
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
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_scrolling.textCountdown
import java.util.Calendar
import java.util.LinkedList

class ScrollingActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private val mediaPlayerQueue = LinkedList<MediaPlayer>()
    private lateinit var timer: CountDownTimer

    // Array of button resource IDs
    private val buttonIds = arrayOf(
        R.id.buttonWitch, R.id.buttonBlackCat, R.id.buttonEvilMan, R.id.buttonCreakyDoor,
        R.id.buttonHorrorAmbience, R.id.buttonMonsterGrowl, R.id.buttonMonsterWalking,
        R.id.buttonScaryScream, R.id.buttonSpookyChains, R.id.buttonThunder, R.id.buttonVampireBat,
        R.id.buttonZombie, R.id.buttonGhostBoo, R.id.buttonWerewolfHowl,
        R.id.buttonPoltergeistVoice, R.id.buttonZombieCome, R.id.buttonCatScream,
        R.id.buttonWraithWail, R.id.buttonSpookyOwl, R.id.buttonChainedGhoul,
        R.id.buttonTerrifiedScream, R.id.buttonHauntedOrgan, R.id.buttonScareCrow,
        R.id.buttonBlowingWind, R.id.buttonLoopingMix, R.id.buttonMovieThemes,
        R.id.buttonGhostlyWhisper, R.id.buttonDraculaLaugh, R.id.buttonWolfCry,
        R.id.buttonKnockKnock, R.id.buttonIgorGrumble, R.id.buttonHorrorMovie,
        R.id.buttonTwoBells, R.id.buttonPainfulMoans, R.id.buttonWitchesCauldron,
        R.id.buttonGhostlyChildren, R.id.buttonHauntedSwamp, R.id.buttonTorturedSouls,
        R.id.buttonChillingHorn
    )

    private val buttons = mutableListOf<Button>()

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

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        showFeedbackDialog() // Prompt users to rate app via in-app review

        updateCountdown() // Update the display with the remaining days until Halloween

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
        // Initializes and sets click listeners for all buttons.
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId) // Find the button by its resource ID
            buttons.add(button) // Add the button to the list for future reference
            button.setOnClickListener {
                button.startAnimation(buttonAnimation) // Start a button animation on click
                val soundId = getSoundResourceId(buttonId) // Get the associated sound resource ID
                playSound(soundId) // Play the corresponding sound
            }
        }


        // Set an OnClickListener to handle the click event for secretactivity
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

    // Function to get sound resource ID based on the clicked button
    private fun getSoundResourceId(buttonId: Int): Int {
        return when (buttonId) {
            R.id.buttonWitch -> R.raw.witch_laugh
            R.id.buttonBlackCat -> R.raw.black_cat
            R.id.buttonEvilMan -> R.raw.evil_man
            R.id.buttonCreakyDoor -> R.raw.creaky_door
            R.id.buttonHorrorAmbience -> R.raw.horror_ambience
            R.id.buttonMonsterGrowl -> R.raw.monster_growl
            R.id.buttonMonsterWalking -> R.raw.monster_walking
            R.id.buttonSpookyChains -> R.raw.spooky_chains
            R.id.buttonThunder -> R.raw.thunder
            R.id.buttonVampireBat -> R.raw.vampire_bat
            R.id.buttonScaryScream -> R.raw.scary_scream
            R.id.buttonZombie -> R.raw.zombie
            R.id.buttonGhostBoo -> R.raw.ghost_boo
            R.id.buttonWerewolfHowl -> R.raw.werewolf_howl
            R.id.buttonPoltergeistVoice -> R.raw.poltergeist_voice
            R.id.buttonZombieCome -> R.raw.zombie_come
            R.id.buttonCatScream -> R.raw.cat_scream
            R.id.buttonWraithWail -> R.raw.wraith_wail
            R.id.buttonSpookyOwl -> R.raw.spooky_owl
            R.id.buttonChainedGhoul -> R.raw.chained_ghoul
            R.id.buttonTerrifiedScream -> R.raw.terrified_scream
            R.id.buttonHauntedOrgan -> R.raw.haunted_organ
            R.id.buttonScareCrow -> R.raw.scarecrow
            R.id.buttonBlowingWind -> R.raw.blowing_wind
            R.id.buttonGhostlyWhisper -> R.raw.ghostly_whisper
            R.id.buttonDraculaLaugh -> R.raw.dracula_laugh
            R.id.buttonWolfCry -> R.raw.wolf_cry
            R.id.buttonKnockKnock -> R.raw.knock_knock
            R.id.buttonIgorGrumble -> R.raw.igor_grumble
            R.id.buttonHorrorMovie -> R.raw.horror_film
            R.id.buttonTwoBells -> R.raw.warning_bells
            R.id.buttonPainfulMoans -> R.raw.painful_moan
            R.id.buttonWitchesCauldron -> R.raw.bubbles
            R.id.buttonGhostlyChildren -> R.raw.scary_nursery
            R.id.buttonHauntedSwamp -> R.raw.haunted_swamp
            R.id.buttonTorturedSouls -> R.raw.tortured_souls
            R.id.buttonChillingHorn -> R.raw.chilling_horn
            else -> 0 // Default value, you can choose another value or behavior
        }
    }

    // Function to play sound
    private fun playSound(soundId: Int) {
        // Create a new MediaPlayer instance for the current sound
        val mediaPlayer = MediaPlayer.create(this, soundId)

        // Set completion listener to release the MediaPlayer when sound finishes
        mediaPlayer.setOnCompletionListener {
            it.release()
            mediaPlayerQueue.remove(it)
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
