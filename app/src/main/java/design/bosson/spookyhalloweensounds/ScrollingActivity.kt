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
/*
        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }
*/
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
