package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.content_scrolling.bBlackCat
import kotlinx.android.synthetic.main.content_scrolling.bBlowingWind
import kotlinx.android.synthetic.main.content_scrolling.bCatScream
import kotlinx.android.synthetic.main.content_scrolling.bChainedGhoul
import kotlinx.android.synthetic.main.content_scrolling.bChillingHorn
import kotlinx.android.synthetic.main.content_scrolling.bCreakyDoor
import kotlinx.android.synthetic.main.content_scrolling.bCreepyLaugh
import kotlinx.android.synthetic.main.content_scrolling.bDraculaLaugh
import kotlinx.android.synthetic.main.content_scrolling.bFrankenstein
import kotlinx.android.synthetic.main.content_scrolling.bGhostBoo
import kotlinx.android.synthetic.main.content_scrolling.bGhostlyChildren
import kotlinx.android.synthetic.main.content_scrolling.bGhostlyWhisper
import kotlinx.android.synthetic.main.content_scrolling.bHauntedOrgan
import kotlinx.android.synthetic.main.content_scrolling.bHauntedSwamp
import kotlinx.android.synthetic.main.content_scrolling.bHorrorAmbience
import kotlinx.android.synthetic.main.content_scrolling.bHorrorMovie
import kotlinx.android.synthetic.main.content_scrolling.bKnockKnock
import kotlinx.android.synthetic.main.content_scrolling.bLoopingMix
import kotlinx.android.synthetic.main.content_scrolling.bMonsterGrowl
import kotlinx.android.synthetic.main.content_scrolling.bMonsterWalking
import kotlinx.android.synthetic.main.content_scrolling.bMovieThemes
import kotlinx.android.synthetic.main.content_scrolling.bPainfulMoan
import kotlinx.android.synthetic.main.content_scrolling.bPoltergeistVoice
import kotlinx.android.synthetic.main.content_scrolling.bScareCrow
import kotlinx.android.synthetic.main.content_scrolling.bScaryScream
import kotlinx.android.synthetic.main.content_scrolling.bSpookyChains
import kotlinx.android.synthetic.main.content_scrolling.bSpookyOwl
import kotlinx.android.synthetic.main.content_scrolling.bTerrifiedScream
import kotlinx.android.synthetic.main.content_scrolling.bThunderStorm
import kotlinx.android.synthetic.main.content_scrolling.bTorturedSouls
import kotlinx.android.synthetic.main.content_scrolling.bVampireBat
import kotlinx.android.synthetic.main.content_scrolling.bWarningBells
import kotlinx.android.synthetic.main.content_scrolling.bWerewolfHowl
import kotlinx.android.synthetic.main.content_scrolling.bWitchCackle
import kotlinx.android.synthetic.main.content_scrolling.bWitchesCauldron
import kotlinx.android.synthetic.main.content_scrolling.bWolfCry
import kotlinx.android.synthetic.main.content_scrolling.bWraithWail
import kotlinx.android.synthetic.main.content_scrolling.bZombieCall
import kotlinx.android.synthetic.main.content_scrolling.bZombieGroan
import kotlinx.android.synthetic.main.content_scrolling.textCountdown
import java.util.Calendar
import java.util.LinkedList

class ScrollingActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private val mediaPlayerQueue = LinkedList<MediaPlayer>()
    private lateinit var timer: CountDownTimer
    private var halloweenDate: Long = 0
    private var doubleBackToExitPressedOnce = false
    private var currentPlayingButton: View? = null
    private var currentPlayingMediaPlayer: MediaPlayer? = null
    private val mediaPlayerMap = mutableMapOf<View, MediaPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // Obtain the Firebase instances.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        // load button animation
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)
        // Calculate the initial halloweenDate based on the current year's Halloween
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        halloweenDate = calculateHalloweenDate(currentYear)
        // Start the countdown timer
        startCountdown()
        // present Google in-app review dialog
        showFeedbackDialog()
        // Retrieves the view with the ID overlayImageView (ic_key) for SecretActivity
        val overlayImageView = findViewById<View>(R.id.overlayImageView)
        // Set an OnClickListener to handle the click event for SecretActivity
        overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        //initialize the mediaplayer and set it to null
        val mediaPlayer = MediaPlayer()
        currentPlayingMediaPlayer = mediaPlayer

        bLoopingMix.setOnClickListener {
            bLoopingMix.startAnimation(buttonAnimation)
            val intent = Intent(this, LongActivity::class.java)
            this.startActivity(intent)
        }

        bMovieThemes.setOnClickListener {
            bMovieThemes.startAnimation(buttonAnimation)
            val intent = Intent(this, MovieActivity::class.java)
            this.startActivity(intent)
        }

        bWitchCackle.setOnClickListener {
            bWitchCackle.startAnimation(buttonAnimation)
            playSound(bWitchCackle, R.raw.witch_laugh)
        }

        bBlackCat.setOnClickListener {
            bBlackCat.startAnimation(buttonAnimation)
            playSound(bBlackCat, R.raw.black_cat)
        }

        bCreepyLaugh.setOnClickListener {
            bCreepyLaugh.startAnimation(buttonAnimation)
            playSound(bCreepyLaugh, R.raw.evil_man)
        }

        bCreakyDoor.setOnClickListener {
            bCreakyDoor.startAnimation(buttonAnimation)
            playSound(bCreakyDoor, R.raw.creaky_door)
        }

        bHorrorAmbience.setOnClickListener {
            bHorrorAmbience.startAnimation(buttonAnimation)
            playSound(bHorrorAmbience, R.raw.horror_ambience)
        }

        bMonsterGrowl.setOnClickListener {
            bMonsterGrowl.startAnimation(buttonAnimation)
            playSound(bMonsterGrowl, R.raw.monster_growl)
        }

        bMonsterWalking.setOnClickListener {
            bMonsterWalking.startAnimation(buttonAnimation)
            playSound(bMonsterWalking, R.raw.monster_walking)
        }

        bScaryScream.setOnClickListener {
            bScaryScream.startAnimation(buttonAnimation)
            playSound(bScaryScream, R.raw.scary_scream)
        }

        bSpookyChains.setOnClickListener {
            bSpookyChains.startAnimation(buttonAnimation)
            playSound(bSpookyChains, R.raw.spooky_chains)
        }

        bThunderStorm.setOnClickListener {
            bThunderStorm.startAnimation(buttonAnimation)
            playSound(bThunderStorm, R.raw.thunder)
        }

        bVampireBat.setOnClickListener {
            bVampireBat.startAnimation(buttonAnimation)
            playSound(bVampireBat, R.raw.vampire_bat)
        }

        bZombieGroan.setOnClickListener {
            bZombieGroan.startAnimation(buttonAnimation)
            playSound(bZombieGroan, R.raw.zombie)
        }

        bGhostBoo.setOnClickListener {
            bGhostBoo.startAnimation(buttonAnimation)
            playSound(bGhostBoo, R.raw.ghost_boo)
        }

        bWerewolfHowl.setOnClickListener {
            bWerewolfHowl.startAnimation(buttonAnimation)
            playSound(bWerewolfHowl, R.raw.werewolf_howl)
        }

        bPoltergeistVoice.setOnClickListener {
            bPoltergeistVoice.startAnimation(buttonAnimation)
            playSound(bPoltergeistVoice, R.raw.poltergeist_voice)
        }

        bZombieCall.setOnClickListener {
            bZombieCall.startAnimation(buttonAnimation)
            playSound(bZombieCall, R.raw.zombie_come)
        }

        bCatScream.setOnClickListener {
            bCatScream.startAnimation(buttonAnimation)
            playSound(bCatScream, R.raw.cat_scream)
        }

        bWraithWail.setOnClickListener {
            bWraithWail.startAnimation(buttonAnimation)
            playSound(bWraithWail, R.raw.wraith_wail)
        }

        bSpookyOwl.setOnClickListener {
            bSpookyOwl.startAnimation(buttonAnimation)
            playSound(bSpookyOwl, R.raw.spooky_owl)
        }

        bChainedGhoul.setOnClickListener {
            bChainedGhoul.startAnimation(buttonAnimation)
            playSound(bChainedGhoul, R.raw.chained_ghoul)
        }

        bTerrifiedScream.setOnClickListener {
            bTerrifiedScream.startAnimation(buttonAnimation)
            playSound(bTerrifiedScream, R.raw.terrified_scream)
        }

        bHauntedOrgan.setOnClickListener {
            bHauntedOrgan.startAnimation(buttonAnimation)
            playSound(bHauntedOrgan, R.raw.haunted_organ)
        }

        bScareCrow.setOnClickListener {
            bScareCrow.startAnimation(buttonAnimation)
            playSound(bScareCrow, R.raw.scarecrow)
        }

        bBlowingWind.setOnClickListener {
            bBlowingWind.startAnimation(buttonAnimation)
            playSound(bBlowingWind, R.raw.blowing_wind)
        }

        bGhostlyWhisper.setOnClickListener {
            bGhostlyWhisper.startAnimation(buttonAnimation)
            playSound(bGhostlyWhisper, R.raw.ghostly_whisper)
        }

        bDraculaLaugh.setOnClickListener {
            bDraculaLaugh.startAnimation(buttonAnimation)
            playSound(bDraculaLaugh, R.raw.dracula_laugh)
        }

        bWolfCry.setOnClickListener {
            bWolfCry.startAnimation(buttonAnimation)
            playSound(bWolfCry, R.raw.wolf_cry)
        }

        bKnockKnock.setOnClickListener {
            bKnockKnock.startAnimation(buttonAnimation)
            playSound(bKnockKnock, R.raw.knock_knock)
        }

        bFrankenstein.setOnClickListener {
            bFrankenstein.startAnimation(buttonAnimation)
            playSound(bFrankenstein, R.raw.igor_grumble)
        }

        bHorrorMovie.setOnClickListener {
            bHorrorMovie.startAnimation(buttonAnimation)
            playSound(bHorrorMovie, R.raw.horror_film)
        }

        bWarningBells.setOnClickListener {
            bWarningBells.startAnimation(buttonAnimation)
            playSound(bWarningBells, R.raw.warning_bells)
        }

        bPainfulMoan.setOnClickListener {
            bPainfulMoan.startAnimation(buttonAnimation)
            playSound(bPainfulMoan, R.raw.painful_moan)
        }

        bWitchesCauldron.setOnClickListener {
            bWitchesCauldron.startAnimation(buttonAnimation)
            playSound(bWitchesCauldron, R.raw.bubbles)
        }

        bGhostlyChildren.setOnClickListener {
            bGhostlyChildren.startAnimation(buttonAnimation)
            playSound(bGhostlyChildren, R.raw.scary_nursery)
        }

        bHauntedSwamp.setOnClickListener {
            bHauntedSwamp.startAnimation(buttonAnimation)
            playSound(bHauntedSwamp, R.raw.haunted_swamp)
        }

        bTorturedSouls.setOnClickListener {
            bTorturedSouls.startAnimation(buttonAnimation)
            playSound(bTorturedSouls, R.raw.tortured_souls)
        }

        bChillingHorn.setOnClickListener {
            bChillingHorn.startAnimation(buttonAnimation)
            playSound(bChillingHorn, R.raw.chilling_horn)
        }
        // Set an OnClickListener to handle the click event
        overlayImageView.setOnClickListener {
            openSecretActivity()
        }
    }
    // Google in-app review dialog - do not delete
    private fun showFeedbackDialog() {
        val reviewManager = ReviewManagerFactory.create(applicationContext)
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) {
                reviewManager.launchReviewFlow(this, it.result)
            }
        }
    }

    private fun openSecretActivity() {
        // Open the SecretActivity here
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }

    private fun resetTimer() {
        // Calculate the next year's Halloween
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val nextYear = currentYear + 1
        halloweenDate = calculateHalloweenDate(nextYear)

        // Start the countdown timer
        startCountdown()
    }

    private fun startCountdown() {
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown(millisUntilFinished)
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()
    }

    private fun updateCountdown(timeRemaining: Long) {

        when (val remainingDays = (timeRemaining / (1000 * 60 * 60 * 24)).toInt()) {
            0 -> {
                getString(R.string.happy_halloween).also { this.textCountdown.text = it }
            }

            1 -> {
                textCountdown.text = getString(R.string.tomorrow_is_halloween)
            }

            else -> {
                "$remainingDays days until Halloween".also { textCountdown.text = it }
            }
        }
    }
    private fun calculateHalloweenDate(year: Int): Long {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.HOUR_OF_DAY, 23)
        }
        return calendar.timeInMillis
    }
    private fun playSound(button: View, soundId: Int) {
        try {
            val mediaPlayer = mediaPlayerMap[button]

            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                } else {
                    mediaPlayer.start()
                }
            } else {
                val newMediaPlayer = MediaPlayer.create(this, soundId)
                mediaPlayerMap[button] = newMediaPlayer

                newMediaPlayer.setOnCompletionListener {
                    it.release()
                    mediaPlayerMap.remove(button)
                    resetButtonColor(button)
                }

                button.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@ScrollingActivity,
                        R.color.colorButtonPressed
                    )
                )

                newMediaPlayer.start()

                button.setOnLongClickListener {
                    newMediaPlayer.seekTo(0)
                    true
                }
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun resetButtonColor(button: View) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this@ScrollingActivity,
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
    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true

            //display msg
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        } else {

            super.onBackPressed()

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