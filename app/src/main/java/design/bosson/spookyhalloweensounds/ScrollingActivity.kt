package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import design.bosson.spookyhalloweensounds.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var soundManager: SoundManager
    private lateinit var countdownManager: CountdownManager
    private val handler = Handler()

    // Runnable to periodically check the system time and update the countdown
    private val timeCheckRunnable = object : Runnable {
        override fun run() {
            countdownManager.cancelCountdown()
            countdownManager.startCountdown()
            Log.d("TimeCheck", "Recalculating countdown based on current time")
            handler.postDelayed(this, 60000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the main layout and set the content view
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundManager = SoundManager()

        // Set up the toolbar from the main activity layout
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // Tap on overlay image -> SecretActivity
        binding.overlayImageView.setOnClickListener { openSecretActivity() }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        // Setup CountdownManager
        countdownManager = CountdownManager(this) { text ->
            binding.textCountdown.text = text
        }
        countdownManager.startCountdown()
        showFeedbackDialog()

        binding.menuHamburger.setOnClickListener { view -> showPopupMenu(view) }

        // Navigation buttons
        binding.bLoopingMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            startActivity(Intent(this, LongActivity::class.java))
        }
        binding.bMovieThemes.setOnClickListener {
            it.startAnimation(buttonAnimation)
            startActivity(Intent(this, MovieActivity::class.java))
        }
        binding.bPartySongs.setOnClickListener {
            it.startAnimation(buttonAnimation)
            startActivity(Intent(this, PartySongsActivity::class.java))
        }

        // Sound buttons
        binding.bWitchCackle.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.witch_laugh, it)
        }
        binding.bBlackCat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.black_cat, it)
        }
        binding.bCreepyLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.evil_man, it)
        }
        binding.bCreakyDoor.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.creaky_door, it)
        }
        binding.bHorrorAmbience.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.horror_ambience, it)
        }
        binding.bMonsterGrowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.monster_growl, it)
        }
        binding.bMonsterWalking.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.monster_walking, it)
        }
        binding.bScaryScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.scary_scream, it)
        }
        binding.bSpookyChains.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.spooky_chains, it)
        }
        binding.bThunderStorm.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.thunder, it)
        }
        binding.bVampireBat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.vampire_bat, it)
        }
        binding.bZombieGroan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.zombie, it)
        }
        binding.bGhostBoo.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.ghost_boo, it)
        }
        binding.bWerewolfHowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.werewolf_howl, it)
        }
        binding.bPoltergeistVoice.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.poltergeist_voice, it)
        }
        binding.bZombieCall.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.zombie_come, it)
        }
        binding.bCatScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.cat_scream, it)
        }
        binding.bWraithWail.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.wraith_wail, it)
        }
        binding.bSpookyOwl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.spooky_owl, it)
        }
        binding.bChainedGhoul.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.chained_ghoul, it)
        }
        binding.bTerrifiedScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.terrified_scream, it)
        }
        binding.bHauntedOrgan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.haunted_organ, it)
        }
        binding.bScareCrow.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.scarecrow, it)
        }
        binding.bBlowingWind.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.blowing_wind, it)
        }
        binding.bGhostlyWhisper.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.ghostly_whisper, it)
        }
        binding.bDraculaLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.dracula_laugh, it)
        }
        binding.bWolfCry.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.wolf_cry, it)
        }
        binding.bKnockKnock.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.knock_knock, it)
        }
        binding.bFrankenstein.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.igor_grumble, it)
        }
        binding.bHorrorMovie.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.horror_film, it)
        }
        binding.bWarningBells.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.warning_bells, it)
        }
        binding.bPainfulMoan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.painful_moan, it)
        }
        binding.bWitchesCauldron.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.bubbles, it)
        }
        binding.bGhostlyChildren.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.scary_nursery, it)
        }
        binding.bHauntedSwamp.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.haunted_swamp, it)
        }
        binding.bTorturedSouls.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.tortured_souls, it)
        }
        binding.bChillingHorn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this, R.raw.chilling_horn, it)
        }
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(timeCheckRunnable, 60000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(timeCheckRunnable)
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
        startActivity(Intent(this, SecretActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        countdownManager.cancelCountdown()
        // soundManager.releaseAllSounds(this)
        // resetAllButtons()
    }

    private fun resetAllButtons() {
        val buttons: List<Button> = listOf(
            binding.bWitchCackle, binding.bBlackCat, binding.bCreepyLaugh,
            binding.bCreakyDoor, binding.bHorrorAmbience, binding.bMonsterGrowl,
            binding.bMonsterWalking, binding.bScaryScream, binding.bSpookyChains,
            binding.bThunderStorm, binding.bVampireBat, binding.bZombieGroan,
            binding.bGhostBoo, binding.bWerewolfHowl, binding.bPoltergeistVoice,
            binding.bZombieCall, binding.bCatScream, binding.bWraithWail,
            binding.bSpookyOwl, binding.bChainedGhoul, binding.bTerrifiedScream,
            binding.bHauntedOrgan, binding.bScareCrow, binding.bBlowingWind,
            binding.bGhostlyWhisper, binding.bDraculaLaugh, binding.bWolfCry,
            binding.bKnockKnock, binding.bFrankenstein, binding.bHorrorMovie,
            binding.bWarningBells, binding.bPainfulMoan, binding.bWitchesCauldron,
            binding.bGhostlyChildren, binding.bHauntedSwamp, binding.bTorturedSouls,
            binding.bChillingHorn
        )
        buttons.forEach { btn ->
            soundManager.resetButtonDrawable(this, btn)
            soundManager.resetButtonColor(this, btn)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
        popupMenu.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, DeveloperActivity::class.java))
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
                }
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
        }
        return true
    }
}
