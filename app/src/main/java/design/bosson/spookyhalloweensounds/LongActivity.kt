package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityLongBinding

class LongActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLongBinding
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.colorAccent)
        // Ensure icons are white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = ActivityLongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        soundManager = SoundManager()

        binding.menuHamburger.setOnClickListener { view ->
            showPopupMenu(view)
        }

        // Use the binding to access views in the layout
        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        // Initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        // Set up button click listeners
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

    override fun onDestroy() {
        super.onDestroy()
        soundManager.releaseAllSounds()  // Delegate media release to SoundManager
    }

    override fun onPause() {
        super.onPause()
        soundManager.releaseAllSounds()  // Pause and release media
        resetAllButtons()
    }

    private fun resetAllButtons() {
        // Reset each button to its original color and drawable
        val buttons = listOf(binding.bTerrorMix, binding.bHauntedMix, binding.bLongMix, binding.bSpaceTerror, binding.bDontLetIn)

        buttons.forEach { button ->
            soundManager.resetButtonDrawable(this, button)  // Reset drawable to the original
            soundManager.resetButtonColor(this, button)  // Reset color to the original
        }
    }

    private fun openSecretActivity() {
        val intent = Intent(this, SecretActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_scrolling, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }

        popupMenu.show()
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

        } /*else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }*/

        return true
    }
}
