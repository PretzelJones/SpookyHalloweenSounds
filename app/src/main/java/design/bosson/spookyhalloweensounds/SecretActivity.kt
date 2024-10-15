package design.bosson.spookyhalloweensounds

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivitySecretBinding

class SecretActivity : BaseActivity() {  // Extend BaseActivity

    private lateinit var binding: ActivitySecretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivitySecretBinding.inflate(layoutInflater)
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

        // Setting up button click listeners
        binding.bTheGhostSong.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@SecretActivity, R.raw.the_ghost_song, it)
        }
        binding.bTheOldTape.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@SecretActivity, R.raw.the_old_tape, it)
        }
        binding.bChillingCries.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@SecretActivity, R.raw.chilling_cries, it)
        }
        binding.bCriesFromHell.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@SecretActivity, R.raw.cries_from_hell, it)
        }
    }

    /**
     * Provide the list of all buttons in SecretActivity.
     */
    override fun getAllButtons(): List<Button> {
        return listOf(
            binding.bTheGhostSong,
            binding.bTheOldTape,
            binding.bChillingCries,
            binding.bCriesFromHell
        )
    }

    /**
     * Resets all buttons in MovieActivity.
     */
    override fun resetAllButtons() {
        soundManager.resetAllButtons(this, getAllButtons())
    }

    /**
     * Displays the popup menu when the hamburger icon is clicked.
     */
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }

        popupMenu.show()
    }

    /**
     * Inflates the options menu. Currently, no menu items are inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    /**
     * Handles selection of menu items.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_settings -> {
                val intent = Intent(this, DeveloperActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
                }
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}