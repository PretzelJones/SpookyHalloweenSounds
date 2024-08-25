package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        soundManager = SoundManager()

        window.statusBarColor = getColor(R.color.colorAccent)
        // Ensure icons are white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        binding.menuHamburger.setOnClickListener { view ->
            showPopupMenu(view)
        }

        binding.overlayImageView.setOnClickListener {
            openSecretActivity()
        }

        // Set click listeners for each button
        binding.bHalloween.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.halloween, it)
        }
        binding.bExorcist.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.exorcist, it)
        }
        binding.bShining.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.shining, it)
        }
        binding.bElmStreet.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.elm_street, it)
        }
        binding.bFriday.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.vorhees, it)
        }
        binding.bAmityville.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.amityville, it)
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
        val buttons = listOf(binding.bHalloween, binding.bExorcist, binding.bShining, binding.bElmStreet, binding.bFriday, binding.bAmityville)

        buttons.forEach { button ->
            soundManager.resetButtonDrawable(this, button)  // Reset drawable to the original
            soundManager.resetButtonColor(this, button)  // Reset color to the original
        }
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
