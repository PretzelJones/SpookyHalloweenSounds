package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
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
            playSound(R.raw.witch_laugh)
        }

        bBlackCat.setOnClickListener {
            bBlackCat.startAnimation(buttonAnimation)
            playSound(R.raw.black_cat)
        }

        bCreepyLaugh.setOnClickListener {
            bCreepyLaugh.startAnimation(buttonAnimation)
            playSound(R.raw.evil_man)
        }

        bCreakyDoor.setOnClickListener {
            bCreakyDoor.startAnimation(buttonAnimation)
            playSound(R.raw.creaky_door)
        }

        bHorrorAmbience.setOnClickListener {
            bHorrorAmbience.startAnimation(buttonAnimation)
            playSound(R.raw.horror_ambience)
        }

        bMonsterGrowl.setOnClickListener {
            bMonsterGrowl.startAnimation(buttonAnimation)
            playSound(R.raw.monster_growl)
        }

        bMonsterWalking.setOnClickListener {
            bMonsterWalking.startAnimation(buttonAnimation)
            playSound(R.raw.monster_walking)
        }

        bScaryScream.setOnClickListener {
            bScaryScream.startAnimation(buttonAnimation)
            playSound(R.raw.scary_scream)
        }

        bSpookyChains.setOnClickListener {
            bSpookyChains.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_chains)
        }

        bThunderStorm.setOnClickListener {
            bThunderStorm.startAnimation(buttonAnimation)
            playSound(R.raw.thunder)
        }

        bVampireBat.setOnClickListener {
            bVampireBat.startAnimation(buttonAnimation)
            playSound(R.raw.vampire_bat)
        }

        bZombieGroan.setOnClickListener {
            bZombieGroan.startAnimation(buttonAnimation)
            playSound(R.raw.zombie)
        }

        bGhostBoo.setOnClickListener {
            bGhostBoo.startAnimation(buttonAnimation)
            playSound(R.raw.ghost_boo)
        }

        bWerewolfHowl.setOnClickListener {
            bWerewolfHowl.startAnimation(buttonAnimation)
            playSound(R.raw.werewolf_howl)
        }

        bPoltergeistVoice.setOnClickListener {
            bPoltergeistVoice.startAnimation(buttonAnimation)
            playSound(R.raw.poltergeist_voice)
        }

        bZombieCall.setOnClickListener {
            bZombieCall.startAnimation(buttonAnimation)
            playSound(R.raw.zombie_come)
        }

        bCatScream.setOnClickListener {
            bCatScream.startAnimation(buttonAnimation)
            playSound(R.raw.cat_scream)
        }

        bWraithWail.setOnClickListener {
            bWraithWail.startAnimation(buttonAnimation)
            playSound(R.raw.wraith_wail)
        }

        bSpookyOwl.setOnClickListener {
            bSpookyOwl.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_owl)
        }

        bChainedGhoul.setOnClickListener {
            bChainedGhoul.startAnimation(buttonAnimation)
            playSound(R.raw.chained_ghoul)
        }

        bTerrifiedScream.setOnClickListener {
            bTerrifiedScream.startAnimation(buttonAnimation)
            playSound(R.raw.terrified_scream)
        }

        bHauntedOrgan.setOnClickListener {
            bHauntedOrgan.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_organ)
        }

        bScareCrow.setOnClickListener {
            bScareCrow.startAnimation(buttonAnimation)
            playSound(R.raw.scarecrow)
        }

        bBlowingWind.setOnClickListener {
            bBlowingWind.startAnimation(buttonAnimation)
            playSound(R.raw.blowing_wind)
        }

        bGhostlyWhisper.setOnClickListener {
            bGhostlyWhisper.startAnimation(buttonAnimation)
            playSound(R.raw.ghostly_whisper)
        }

        bDraculaLaugh.setOnClickListener {
            bDraculaLaugh.startAnimation(buttonAnimation)
            playSound(R.raw.dracula_laugh)
        }

        bWolfCry.setOnClickListener {
            bWolfCry.startAnimation(buttonAnimation)
            playSound(R.raw.wolf_cry)
        }

        bKnockKnock.setOnClickListener {
            bKnockKnock.startAnimation(buttonAnimation)
            playSound(R.raw.knock_knock)
        }

        bFrankenstein.setOnClickListener {
            bFrankenstein.startAnimation(buttonAnimation)
            playSound(R.raw.igor_grumble)
        }

        bHorrorMovie.setOnClickListener {
            bHorrorMovie.startAnimation(buttonAnimation)
            playSound(R.raw.horror_film)
        }

        bWarningBells.setOnClickListener {
            bWarningBells.startAnimation(buttonAnimation)
            playSound(R.raw.warning_bells)
        }

        bPainfulMoan.setOnClickListener {
            bPainfulMoan.startAnimation(buttonAnimation)
            playSound(R.raw.painful_moan)
        }

        bWitchesCauldron.setOnClickListener {
            bWitchesCauldron.startAnimation(buttonAnimation)
            playSound(R.raw.bubbles)
        }

        bGhostlyChildren.setOnClickListener {
            bGhostlyChildren.startAnimation(buttonAnimation)
            playSound(R.raw.scary_nursery)
        }

        bHauntedSwamp.setOnClickListener {
            bHauntedSwamp.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_swamp)
        }

        bTorturedSouls.setOnClickListener {
            bTorturedSouls.startAnimation(buttonAnimation)
            playSound(R.raw.tortured_souls)
        }

        bChillingHorn.setOnClickListener {
            bChillingHorn.startAnimation(buttonAnimation)
            playSound(R.raw.chilling_horn)
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
            if(it.isSuccessful) {
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
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }
        return calendar.timeInMillis
    }
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
            }/*else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }*/
        }
        return true
    }
}
