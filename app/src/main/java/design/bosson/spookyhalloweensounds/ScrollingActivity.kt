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
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import design.bosson.spookyhalloweensounds.databinding.ActivityScrollingBinding
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import java.util.Calendar
import java.util.LinkedList

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    
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

        // Inflate the main layout and set the content view
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar from the main activity layout
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // Use the binding to access views in the layout
        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        halloweenDate = calculateHalloweenDate(currentYear)

        startCountdown()
        showFeedbackDialog()

        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        binding.menuHamburger.setOnClickListener { view ->
            showPopupMenu(view)
        }

        binding.bLoopingMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            val intent = Intent(this, LongActivity::class.java)
            this.startActivity(intent)
        }

        binding.bMovieThemes.setOnClickListener {
            it.startAnimation(buttonAnimation)
            val intent = Intent(this, MovieActivity::class.java)
            this.startActivity(intent)
        }

        binding.bWitchCackle.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.witch_laugh, it)
        }

        binding.bBlackCat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.black_cat, it)
        }

        binding.bCreepyLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.evil_man, it)
        }

        binding.bCreakyDoor.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.creaky_door, it)
        }

        binding.bHorrorAmbience.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.horror_ambience, it)
        }

        binding.bMonsterGrowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.monster_growl, it)
        }

        binding.bMonsterWalking.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.monster_walking, it)
        }

        binding.bScaryScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.scary_scream, it)
        }

        binding.bSpookyChains.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_chains, it)
        }

        binding.bThunderStorm.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.thunder, it)
        }

        binding.bVampireBat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.vampire_bat, it)
        }

        binding.bZombieGroan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.zombie, it)
        }

        binding.bGhostBoo.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.ghost_boo, it)
        }

        binding.bWerewolfHowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.werewolf_howl, it)
        }

        binding.bPoltergeistVoice.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.poltergeist_voice, it)
        }

        binding.bZombieCall.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.zombie_come, it)
        }

        binding.bCatScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.cat_scream, it)
        }

        binding.bWraithWail.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.wraith_wail, it)
        }

        binding.bSpookyOwl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_owl, it)
        }

        binding.bChainedGhoul.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.chained_ghoul, it)
        }

        binding.bTerrifiedScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.terrified_scream, it)
        }

        binding.bHauntedOrgan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_organ, it)
        }

        binding.bScareCrow.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.scarecrow, it)
        }

        binding.bBlowingWind.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.blowing_wind, it)
        }

        binding.bGhostlyWhisper.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.ghostly_whisper, it)
        }

        binding.bDraculaLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.dracula_laugh, it)
        }

        binding.bWolfCry.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.wolf_cry, it)
        }

        binding.bKnockKnock.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.knock_knock, it)
        }

        binding.bFrankenstein.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.igor_grumble, it)
        }

        binding.bHorrorMovie.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.horror_film, it)
        }

        binding.bWarningBells.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.warning_bells, it)
        }

        binding.bPainfulMoan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.painful_moan, it)
        }

        binding.bWitchesCauldron.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.bubbles, it)
        }

        binding.bGhostlyChildren.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.scary_nursery, it)
        }

        binding.bHauntedSwamp.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_swamp, it)
        }

        binding.bTorturedSouls.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.tortured_souls, it)
        }

        binding.bChillingHorn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            playSound(R.raw.chilling_horn, it)
        }
    }

    private fun updateCountdown(timeRemaining: Long) {
        when (val remainingDays = (timeRemaining / (1000 * 60 * 60 * 24)).toInt()) {
            0 -> {
                getString(R.string.happy_halloween).also { binding.textCountdown.text = it }
            }
            1 -> {
                binding.textCountdown.text = getString(R.string.tomorrow_is_halloween)
            }
            else -> {
                "$remainingDays days until Halloween".also { binding.textCountdown.text = it }
            }
        }
    }

    private fun playSound(soundId: Int, button: View) {
        try {
            val existingMediaPlayer = mediaPlayerMap[button]

            if (existingMediaPlayer != null) {
                if (existingMediaPlayer.isPlaying) {
                    existingMediaPlayer.pause()
                } else {
                    existingMediaPlayer.start()
                }
            } else {
                val mediaPlayer = MediaPlayer.create(this, soundId)

                mediaPlayer.setOnCompletionListener {
                    it.release()
                    mediaPlayerMap.remove(button)
                    resetButtonColor(button)
                }

                mediaPlayerMap[button] = mediaPlayer

                button.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.colorButtonPressed)
                )

                mediaPlayer.start()

                button.setOnLongClickListener {
                    mediaPlayer.seekTo(0)
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
            ContextCompat.getColor(this, R.color.colorButton)
        )
    }

    private fun showFeedbackDialog() {
        val reviewManager = ReviewManagerFactory.create(applicationContext)
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) {
                reviewManager.launchReviewFlow(this, it.result)
            }
        }
    }

    private fun openSecretActivity() {
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
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

    private fun resetTimer() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val nextYear = currentYear + 1
        halloweenDate = calculateHalloweenDate(nextYear)
        startCountdown()
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

    override fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    private fun showPopupMenu(view: View) {
        // Create a PopupMenu
        val popupMenu = PopupMenu(this, view)
        // Inflate the popup menu using the menu resource file
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)

        // Handle menu item clicks by forwarding them to onOptionsItemSelected
        popupMenu.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }

        // Show the popup menu
        popupMenu.show()
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




/*
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
            playSound(R.raw.witch_laugh, bWitchCackle)
        }

        bBlackCat.setOnClickListener {
            bBlackCat.startAnimation(buttonAnimation)
            playSound(R.raw.black_cat, bBlackCat)
        }

        bCreepyLaugh.setOnClickListener {
            bCreepyLaugh.startAnimation(buttonAnimation)
            playSound(R.raw.evil_man, bCreepyLaugh)
        }

        bCreakyDoor.setOnClickListener {
            bCreakyDoor.startAnimation(buttonAnimation)
            playSound(R.raw.creaky_door, bCreakyDoor)
        }

        bHorrorAmbience.setOnClickListener {
            bHorrorAmbience.startAnimation(buttonAnimation)
            playSound(R.raw.horror_ambience, bHorrorAmbience)
        }

        bMonsterGrowl.setOnClickListener {
            bMonsterGrowl.startAnimation(buttonAnimation)
            playSound(R.raw.monster_growl, bMonsterGrowl)
        }

        bMonsterWalking.setOnClickListener {
            bMonsterWalking.startAnimation(buttonAnimation)
            playSound(R.raw.monster_walking, bMonsterWalking)
        }

        bScaryScream.setOnClickListener {
            bScaryScream.startAnimation(buttonAnimation)
            playSound(R.raw.scary_scream, bScaryScream)
        }

        bSpookyChains.setOnClickListener {
            bSpookyChains.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_chains, bSpookyChains)
        }

        bThunderStorm.setOnClickListener {
            bThunderStorm.startAnimation(buttonAnimation)
            playSound(R.raw.thunder, bThunderStorm)
        }

        bVampireBat.setOnClickListener {
            bVampireBat.startAnimation(buttonAnimation)
            playSound(R.raw.vampire_bat, bVampireBat)
        }

        bZombieGroan.setOnClickListener {
            bZombieGroan.startAnimation(buttonAnimation)
            playSound(R.raw.zombie, bZombieGroan)
        }

        bGhostBoo.setOnClickListener {
            bGhostBoo.startAnimation(buttonAnimation)
            playSound(R.raw.ghost_boo, bGhostBoo)
        }

        bWerewolfHowl.setOnClickListener {
            bWerewolfHowl.startAnimation(buttonAnimation)
            playSound(R.raw.werewolf_howl, bWerewolfHowl)
        }

        bPoltergeistVoice.setOnClickListener {
            bPoltergeistVoice.startAnimation(buttonAnimation)
            playSound(R.raw.poltergeist_voice, bPoltergeistVoice)
        }

        bZombieCall.setOnClickListener {
            bZombieCall.startAnimation(buttonAnimation)
            playSound(R.raw.zombie_come, bZombieCall)
        }

        bCatScream.setOnClickListener {
            bCatScream.startAnimation(buttonAnimation)
            playSound(R.raw.cat_scream, bCatScream)
        }

        bWraithWail.setOnClickListener {
            bWraithWail.startAnimation(buttonAnimation)
            playSound(R.raw.wraith_wail, bWraithWail)
        }

        bSpookyOwl.setOnClickListener {
            bSpookyOwl.startAnimation(buttonAnimation)
            playSound(R.raw.spooky_owl, bSpookyOwl)
        }

        bChainedGhoul.setOnClickListener {
            bChainedGhoul.startAnimation(buttonAnimation)
            playSound(R.raw.chained_ghoul, bChainedGhoul)
        }

        bTerrifiedScream.setOnClickListener {
            bTerrifiedScream.startAnimation(buttonAnimation)
            playSound(R.raw.terrified_scream, bTerrifiedScream)
        }

        bHauntedOrgan.setOnClickListener {
            bHauntedOrgan.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_organ, bHauntedOrgan)
        }

        bScareCrow.setOnClickListener {
            bScareCrow.startAnimation(buttonAnimation)
            playSound(R.raw.scarecrow, bScareCrow)
        }

        bBlowingWind.setOnClickListener {
            bBlowingWind.startAnimation(buttonAnimation)
            playSound(R.raw.blowing_wind, bBlowingWind)
        }

        bGhostlyWhisper.setOnClickListener {
            bGhostlyWhisper.startAnimation(buttonAnimation)
            playSound(R.raw.ghostly_whisper, bGhostlyWhisper)
        }

        bDraculaLaugh.setOnClickListener {
            bDraculaLaugh.startAnimation(buttonAnimation)
            playSound(R.raw.dracula_laugh, bDraculaLaugh)
        }

        bWolfCry.setOnClickListener {
            bWolfCry.startAnimation(buttonAnimation)
            playSound(R.raw.wolf_cry, bWolfCry)
        }

        bKnockKnock.setOnClickListener {
            bKnockKnock.startAnimation(buttonAnimation)
            playSound(R.raw.knock_knock, bKnockKnock)
        }

        bFrankenstein.setOnClickListener {
            bFrankenstein.startAnimation(buttonAnimation)
            playSound(R.raw.igor_grumble, bFrankenstein)
        }

        bHorrorMovie.setOnClickListener {
            bHorrorMovie.startAnimation(buttonAnimation)
            playSound(R.raw.horror_film, bHorrorMovie)
        }

        bWarningBells.setOnClickListener {
            bWarningBells.startAnimation(buttonAnimation)
            playSound(R.raw.warning_bells, bWarningBells)
        }

        bPainfulMoan.setOnClickListener {
            bPainfulMoan.startAnimation(buttonAnimation)
            playSound(R.raw.painful_moan, bPainfulMoan)
        }

        bWitchesCauldron.setOnClickListener {
            bWitchesCauldron.startAnimation(buttonAnimation)
            playSound(R.raw.bubbles, bWitchesCauldron)
        }

        bGhostlyChildren.setOnClickListener {
            bGhostlyChildren.startAnimation(buttonAnimation)
            playSound(R.raw.scary_nursery, bGhostlyChildren)
        }

        bHauntedSwamp.setOnClickListener {
            bHauntedSwamp.startAnimation(buttonAnimation)
            playSound(R.raw.haunted_swamp, bHauntedSwamp)
        }

        bTorturedSouls.setOnClickListener {
            bTorturedSouls.startAnimation(buttonAnimation)
            playSound(R.raw.tortured_souls, bTorturedSouls)
        }

        bChillingHorn.setOnClickListener {
            bChillingHorn.startAnimation(buttonAnimation)
            playSound(R.raw.chilling_horn, bChillingHorn)
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
            //set(Calendar.MINUTE, 59)
            //set(Calendar.SECOND, 59)
        }
        return calendar.timeInMillis
    }
    private fun playSound(soundId: Int, button: View) {
        try {
            // Check if a MediaPlayer is associated with this button
            val existingMediaPlayer = mediaPlayerMap[button]

            if (existingMediaPlayer != null) {
                if (existingMediaPlayer.isPlaying) {
                    // If the MediaPlayer associated with this button is playing, pause it
                    existingMediaPlayer.pause()
                } else {
                    // If the MediaPlayer associated with this button is paused, resume playback
                    existingMediaPlayer.start()
                }
            } else {
                // Create a new MediaPlayer instance for the current sound
                val mediaPlayer = MediaPlayer.create(this, soundId)

                // Set completion listener to release the MediaPlayer when sound finishes
                mediaPlayer.setOnCompletionListener {
                    it.release()
                    mediaPlayerMap.remove(button)

                    // Change the button color back to colorButton when sound finishes
                    resetButtonColor(button)
                }

                // Add the new MediaPlayer to the map and start playing the current sound
                mediaPlayerMap[button] = mediaPlayer

                // Change the button color to colorButtonPressed
                button.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this@ScrollingActivity,
                        R.color.colorButtonPressed
                    )
                )

                mediaPlayer.start()

                // Add a long-press listener to restart the audio
                button.setOnLongClickListener {
                    mediaPlayer.seekTo(0) // Restart the audio from the beginning
                    true // Consume the long-press event
                }
            }
        } catch (e: Resources.NotFoundException) {
            // Handle the case where the resource with the given soundId is not found
            e.printStackTrace()
        } catch (e: Exception) {
            // Handle other exceptions
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
    /* original code. Sound button stuck when moving back and forth between activities
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
                            this@ScrollingActivity,
                            R.color.colorButton
                        )
                    )
                }
            }

            // Change the button color to colorButtonPressed
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@ScrollingActivity,
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
                        this@ScrollingActivity,
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
        }
    }*/
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerQueue.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayerQueue.clear()
    }
/* original code to stop playing when moving back and forth between activities
private fun releaseAllMediaPlayers() {
    mediaPlayerQueue.forEach { mediaPlayer ->
        mediaPlayer.release()
    }
    mediaPlayerQueue.clear()
} */
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
/* original code to stop playing when moving back and forth between activities
override fun onPause() {
    super.onPause()
    releaseAllMediaPlayers()
}
override fun onResume() {
    super.onResume()
    // Reset the button colors here
    currentPlayingButton?.backgroundTintList = ColorStateList.valueOf(
        ContextCompat.getColor(this, R.color.colorButton)
    )
}*/
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
        }/*else if (id == R.id.secret) {
        val intent = Intent(this, SecretActivity::class.java)
        this.startActivity(intent)
    }*/
    }
    return true
}
}

 */
