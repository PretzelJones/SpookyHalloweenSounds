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

    private lateinit var bWitchLaugh: Button
    private lateinit var bBlackCat: Button
    private lateinit var bEvilMan: Button
    private lateinit var bCreakyDoor: Button
    private lateinit var bHorrorAmbience: Button
    private lateinit var bMonsterGrowl: Button
    private lateinit var bMonsterWalking: Button
    private lateinit var bScaryScream: Button
    private lateinit var bSpookyChains: Button
    private lateinit var bThunder: Button
    private lateinit var bVampireBat: Button
    private lateinit var bZombie: Button
    private lateinit var bGhostBoo: Button
    private lateinit var bWerewolfHowl: Button
    private lateinit var bPoltergeistVoice: Button
    private lateinit var bZombieCome: Button
    private lateinit var bCatScream: Button
    private lateinit var bWraithWail: Button
    private lateinit var bSpookyOwl: Button
    private lateinit var bChainedGhoul: Button
    private lateinit var bTerrifiedScream: Button
    private lateinit var bHauntedOrgan: Button
    private lateinit var bScareCrow: Button
    private lateinit var bBlowingWind: Button
    private lateinit var bLoopingMix: Button
    private lateinit var bMovieThemes: Button
    private lateinit var bGhostlyWhisper: Button
    private lateinit var bDraculaLaugh: Button
    private lateinit var bWolfCry: Button
    private lateinit var bKnockKnock: Button
    private lateinit var bIgorGrumble: Button
    private lateinit var bHorrorMovie: Button
    private lateinit var bTwoBells: Button
    private lateinit var bPainfulMoan: Button
    private lateinit var bWitchesCauldron: Button
    private lateinit var bGhostlyChildren: Button
    private lateinit var bHauntedSwamp: Button
    private lateinit var bTorturedSouls: Button
    private lateinit var bChillingHorn: Button

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private val mediaPlayerQueue = LinkedList<MediaPlayer>()
    private lateinit var timer: CountDownTimer

    private var halloweenDate = Calendar.getInstance().apply {
        set(Calendar.MONTH, Calendar.NOVEMBER)
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis

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

        // present Google in-app review dialog
        showFeedbackDialog()

        val overlayImageView = findViewById<View>(R.id.overlayImageView)

        // Set the target date to October 31 of the current year
        // Countdown to Halloween
        updateCountdown()
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown()
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()

        bLoopingMix = findViewById(R.id.buttonLoopingMix)
        bMovieThemes = findViewById(R.id.buttonMovieThemes)
        bWitchLaugh = findViewById(R.id.buttonWitch)
        bBlackCat = findViewById(R.id.buttonBlackCat)
        bEvilMan = findViewById(R.id.buttonEvilMan)
        bCreakyDoor = findViewById(R.id.buttonCreakyDoor)
        bHorrorAmbience = findViewById(R.id.buttonHorrorAmbience)
        bMonsterGrowl = findViewById(R.id.buttonMonsterGrowl)
        bMonsterWalking = findViewById(R.id.buttonMonsterWalking)
        bScaryScream = findViewById(R.id.buttonScaryScream)
        bSpookyChains = findViewById(R.id.buttonSpookyChains)
        bThunder = findViewById(R.id.buttonThunder)
        bVampireBat = findViewById(R.id.buttonVampireBat)
        bZombie = findViewById(R.id.buttonZombie)
        bGhostBoo = findViewById(R.id.buttonGhostBoo)
        bWerewolfHowl = findViewById(R.id.buttonWerewolfHowl)
        bPoltergeistVoice = findViewById(R.id.buttonPoltergeistVoice)
        bZombieCome = findViewById(R.id.buttonZombieCome)
        bCatScream = findViewById(R.id.buttonCatScream)
        bWraithWail = findViewById(R.id.buttonWraithWail)
        bSpookyOwl = findViewById(R.id.buttonSpookyOwl)
        bChainedGhoul = findViewById(R.id.buttonChainedGhoul)
        bTerrifiedScream = findViewById(R.id.buttonTerrifiedScream)
        bHauntedOrgan = findViewById(R.id.buttonHauntedOrgan)
        bScareCrow = findViewById(R.id.buttonScareCrow)
        bBlowingWind = findViewById(R.id.buttonBlowingWind)
        bGhostlyWhisper = findViewById(R.id.buttonGhostlyWhisper)
        bDraculaLaugh = findViewById(R.id.buttonDraculaLaugh)
        bWolfCry = findViewById(R.id.buttonWolfCry)
        bKnockKnock = findViewById(R.id.buttonKnockKnock)
        bIgorGrumble = findViewById(R.id.buttonIgorGrumble)
        bHorrorMovie = findViewById(R.id.buttonHorrorMovie)
        bTwoBells = findViewById(R.id.buttonTwoBells)
        bPainfulMoan = findViewById(R.id.buttonPainfulMoans)
        bWitchesCauldron = findViewById(R.id.buttonWitchesCauldron)
        bGhostlyChildren = findViewById(R.id.buttonGhostlyChildren)
        bHauntedSwamp = findViewById(R.id.buttonHauntedSwamp)
        bTorturedSouls = findViewById(R.id.buttonTorturedSouls)
        bChillingHorn = findViewById(R.id.buttonChillingHorn)

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

        bWitchLaugh.setOnClickListener {
            bWitchLaugh.startAnimation(buttonAnimation)
            playSound(R.raw.witch_laugh)
        }

        bBlackCat.setOnClickListener {
            bBlackCat.startAnimation(buttonAnimation)
            playSound(R.raw.black_cat)
        }

        bEvilMan.setOnClickListener {
            bEvilMan.startAnimation(buttonAnimation)
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

        bThunder.setOnClickListener {
            bThunder.startAnimation(buttonAnimation)
            playSound(R.raw.thunder)
        }

        bVampireBat.setOnClickListener {
            bVampireBat.startAnimation(buttonAnimation)
            playSound(R.raw.vampire_bat)
        }

        bZombie.setOnClickListener {
            bZombie.startAnimation(buttonAnimation)
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

        bZombieCome.setOnClickListener {
            bZombieCome.startAnimation(buttonAnimation)
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

        bIgorGrumble.setOnClickListener {
            bIgorGrumble.startAnimation(buttonAnimation)
            playSound(R.raw.igor_grumble)
        }

        bHorrorMovie.setOnClickListener {
            bHorrorMovie.startAnimation(buttonAnimation)
            playSound(R.raw.horror_film)
        }

        bTwoBells.setOnClickListener {
            bTwoBells.startAnimation(buttonAnimation)
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

    private fun resetTimer() {
        // Reset the timer for next year's Halloween
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
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown()
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()
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
            }
        }
        return true
    }
}




