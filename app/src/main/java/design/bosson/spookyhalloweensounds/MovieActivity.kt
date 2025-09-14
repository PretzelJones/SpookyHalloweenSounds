package design.bosson.spookyhalloweensounds

import android.widget.TextView
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityMovieBinding

class MovieActivity : BaseActivity() {  // Now extends BaseActivity

    private lateinit var binding: ActivityMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiate seek info alert dialog
        FeatureDialogHelper.showFeatureDialogIfNeeded(this)

        binding.bHalloween.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bHalloween) },
            getPositionMs = { soundManager.getLongPosition(binding.bHalloween) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bHalloween, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bHalloween) }
        )
        binding.bExorcist.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bExorcist) },
            getPositionMs = { soundManager.getLongPosition(binding.bExorcist) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bExorcist, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bExorcist) }
        )
        binding.bShining.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bShining) },
            getPositionMs = { soundManager.getLongPosition(binding.bShining) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bShining, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bShining) }
        )

        binding.bElmStreet.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bElmStreet) },
            getPositionMs = { soundManager.getLongPosition(binding.bElmStreet) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bElmStreet, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bElmStreet) }
        )

        binding.bFriday.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bFriday) },
            getPositionMs = { soundManager.getLongPosition(binding.bFriday) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bFriday, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bFriday) }
        )

        binding.bAmityville.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bAmityville) },
            getPositionMs = { soundManager.getLongPosition(binding.bAmityville) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bAmityville, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bAmityville) }
        )

        binding.bUnsolvedMysteries.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bUnsolvedMysteries) },
            getPositionMs = { soundManager.getLongPosition(binding.bUnsolvedMysteries) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bUnsolvedMysteries, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bUnsolvedMysteries) }
        )

        binding.bXfiles.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bXfiles) },
            getPositionMs = { soundManager.getLongPosition(binding.bXfiles) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bXfiles, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bXfiles) }
        )

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
        binding.bHalloween.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.halloween, it)
            // inside bHalloween click listener, after playLongSound(...)
            binding.bHalloween.onPlayStateChanged()
        }
        binding.bExorcist.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.exorcist, it)
            binding.bExorcist.onPlayStateChanged()
        }
        binding.bShining.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.shining, it)
            binding.bShining.onPlayStateChanged()
        }
        binding.bElmStreet.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.elm_street, it)
            binding.bElmStreet.onPlayStateChanged()
        }
        binding.bFriday.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.vorhees, it)
            binding.bFriday.onPlayStateChanged()
        }
        binding.bAmityville.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.amityville, it)
            binding.bAmityville.onPlayStateChanged()
        }
        binding.bUnsolvedMysteries.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.unsolved_mysteries, it)
            binding.bUnsolvedMysteries.onPlayStateChanged()
        }
        binding.bXfiles.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.xfiles, it)
            binding.bXfiles.onPlayStateChanged()
        }
/*
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
        binding.bUnsolvedMysteries.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.unsolved_mysteries, it)
        }
        binding.bXfiles.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this@MovieActivity, R.raw.xfiles, it)
        } */
    }

    /**
     * Provide the list of all buttons in MovieActivity.
     */
    /*
    override fun getAllButtons(): List<Button> {
        return listOf(
            binding.bHalloween,
            binding.bExorcist,
            binding.bShining,
            binding.bElmStreet,
            binding.bFriday,
            binding.bAmityville,
            binding.bUnsolvedMysteries,
            binding.bXfiles
        )
    } */
    override fun getAllButtons(): List<TextView> = listOf(
        binding.bHalloween,
        binding.bExorcist,
        binding.bShining,
        binding.bElmStreet,
        binding.bFriday,
        binding.bAmityville,
        binding.bUnsolvedMysteries,
        binding.bXfiles
    )

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
        // menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

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