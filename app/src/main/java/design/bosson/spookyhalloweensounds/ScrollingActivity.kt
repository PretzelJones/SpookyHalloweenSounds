package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
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
import java.util.Calendar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var soundManager: SoundManager
    private lateinit var countdownManager: CountdownManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the main layout and set the content view
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundManager = SoundManager()

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

        // Setup CountdownManager
        countdownManager = CountdownManager(this) { text ->
            binding.textCountdown.text = text // Update the countdown text
        }

        // Start the countdown
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val halloweenDate = calculateHalloweenDate(currentYear)
        countdownManager.startCountdown(halloweenDate)
        //val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        //halloweenDate = calculateHalloweenDate(currentYear)

        //startCountdown()
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
            soundManager.playShortSound(this@ScrollingActivity, R.raw.witch_laugh, it)
        }

        binding.bBlackCat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.black_cat, it)
        }

        binding.bCreepyLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.evil_man, it)
        }

        binding.bCreakyDoor.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.creaky_door, it)
        }

        binding.bHorrorAmbience.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.horror_ambience, it)
        }

        binding.bMonsterGrowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.monster_growl, it)
        }

        binding.bMonsterWalking.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.monster_walking, it)
        }

        binding.bScaryScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.scary_scream, it)
        }

        binding.bSpookyChains.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.spooky_chains, it)
        }

        binding.bThunderStorm.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.thunder, it)
        }

        binding.bVampireBat.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.vampire_bat, it)
        }

        binding.bZombieGroan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.zombie, it)
        }

        binding.bGhostBoo.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.ghost_boo, it)
        }

        binding.bWerewolfHowl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.werewolf_howl, it)
        }

        binding.bPoltergeistVoice.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.poltergeist_voice, it)
        }

        binding.bZombieCall.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.zombie_come, it)
        }

        binding.bCatScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.cat_scream, it)
        }

        binding.bWraithWail.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.wraith_wail, it)
        }

        binding.bSpookyOwl.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.spooky_owl, it)
        }

        binding.bChainedGhoul.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.chained_ghoul, it)
        }

        binding.bTerrifiedScream.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.terrified_scream, it)
        }

        binding.bHauntedOrgan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.haunted_organ, it)
        }

        binding.bScareCrow.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.scarecrow, it)
        }

        binding.bBlowingWind.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.blowing_wind, it)
        }

        binding.bGhostlyWhisper.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.ghostly_whisper, it)
        }

        binding.bDraculaLaugh.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.dracula_laugh, it)
        }

        binding.bWolfCry.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.wolf_cry, it)
        }

        binding.bKnockKnock.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.knock_knock, it)
        }

        binding.bFrankenstein.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.igor_grumble, it)
        }

        binding.bHorrorMovie.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.horror_film, it)
        }

        binding.bWarningBells.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.warning_bells, it)
        }

        binding.bPainfulMoan.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.painful_moan, it)
        }

        binding.bWitchesCauldron.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.bubbles, it)
        }

        binding.bGhostlyChildren.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.scary_nursery, it)
        }

        binding.bHauntedSwamp.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.haunted_swamp, it)
        }

        binding.bTorturedSouls.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.tortured_souls, it)
        }

        binding.bChillingHorn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playShortSound(this@ScrollingActivity, R.raw.chilling_horn, it)
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
/*
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
*/
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

    override fun onPause() {
        super.onPause()

        // Release all sounds using SoundManager
        soundManager.releaseAllSounds()

        // Reset all button colors and states (similar to MovieActivity)
        resetAllButtons()
    }

    private fun resetAllButtons() {
        // Reset each button to its original color and drawable
        val buttons = listOf(
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

        buttons.forEach { button ->
            soundManager.resetButtonDrawable(this, button as Button)  // Reset drawable
            soundManager.resetButtonColor(this, button)  // Reset button color
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
