// app/src/main/java/design/bosson/spookyhalloweensounds/LongActivity.kt
package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityLongBinding

class LongActivity : BaseActivity() {

    private lateinit var binding: ActivityLongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiate seek info alert dialog
        FeatureDialogHelper.showFeatureDialogIfNeeded(this)

        binding.bTerrorMix.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bTerrorMix) },
            getPositionMs = { soundManager.getLongPosition(binding.bTerrorMix) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bTerrorMix, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bTerrorMix) }
        )
        binding.bHauntedMix.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bHauntedMix) },
            getPositionMs = { soundManager.getLongPosition(binding.bHauntedMix) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bHauntedMix, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bHauntedMix) }
        )
        binding.bLongMix.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bLongMix) },
            getPositionMs = { soundManager.getLongPosition(binding.bLongMix) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bLongMix, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bLongMix) }
        )
        binding.bSpaceTerror.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bSpaceTerror) },
            getPositionMs = { soundManager.getLongPosition(binding.bSpaceTerror) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bSpaceTerror, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bSpaceTerror) }
        )
        binding.bDontLetIn.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bDontLetIn) },
            getPositionMs = { soundManager.getLongPosition(binding.bDontLetIn) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bDontLetIn, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bDontLetIn) }
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        window.statusBarColor = getColor(R.color.colorAccent)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        binding.menuHamburger.setOnClickListener { view -> showPopupMenu(view) }
        binding.overlayImageView.setOnClickListener { openSecretActivity() }

        // Clicks
        binding.bTerrorMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.ultra_terror, it)
            binding.bTerrorMix.onPlayStateChanged()
        }
        binding.bHauntedMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.haunted_house, it)
            binding.bHauntedMix.onPlayStateChanged()
        }
        binding.bLongMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.long_mix, it)
            binding.bLongMix.onPlayStateChanged()
        }
        binding.bSpaceTerror.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.space_terror, it)
            binding.bSpaceTerror.onPlayStateChanged()
        }
        binding.bDontLetIn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.dont_let_in, it)
            binding.bDontLetIn.onPlayStateChanged()
        }
    }

    override fun getAllButtons(): List<TextView> = listOf(
        binding.bTerrorMix,
        binding.bHauntedMix,
        binding.bLongMix,
        binding.bSpaceTerror,
        binding.bDontLetIn
    )

    override fun resetAllButtons() {
        soundManager.resetAllButtons(this, getAllButtons())
    }

    private fun openSecretActivity() {
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, DeveloperActivity::class.java)); true
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
                }
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


/*
package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityLongBinding

class LongActivity : BaseActivity() {  // Extend BaseActivity

    private lateinit var binding: ActivityLongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityLongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Prevent title display

        window.statusBarColor = getColor(R.color.colorAccent)
        // Ensure icons are white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        // Initialize button animation for buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        // Setup menu hamburger click listener
        binding.menuHamburger.setOnClickListener { view ->
            showPopupMenu(view)
        }

        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        // Set click listeners for each button
        binding.bTerrorMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@LongActivity, R.raw.ultra_terror, it)
        }
        binding.bHauntedMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@LongActivity, R.raw.haunted_house, it)
        }
        binding.bLongMix.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@LongActivity, R.raw.long_mix, it)
        }
        binding.bSpaceTerror.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@LongActivity, R.raw.space_terror, it)
        }
        binding.bDontLetIn.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@LongActivity, R.raw.dont_let_in, it)
        }
    }

    /**
     * Provide the list of all buttons in LongActivity.
     */
    override fun getAllButtons(): List<Button> {
        return listOf(
            binding.bTerrorMix,
            binding.bHauntedMix,
            binding.bLongMix,
            binding.bSpaceTerror,
            binding.bDontLetIn
        )
    }

    /**
     * Resets all buttons in MovieActivity.
     */
    override fun resetAllButtons() {
        soundManager.resetAllButtons(this, getAllButtons())
    }

    private fun openSecretActivity() {
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }

        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            val intent = Intent(this, DeveloperActivity::class.java)
            this.startActivity(intent)

        } else if (id == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        return true
    }
}
 */