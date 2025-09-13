package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import design.bosson.spookyhalloweensounds.databinding.ActivityPartyBinding

class PartySongsActivity : BaseActivity() {

    private lateinit var binding: ActivityPartyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Seek binds
        binding.bGhostbusters.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bGhostbusters) },
            getPositionMs = { soundManager.getLongPosition(binding.bGhostbusters) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bGhostbusters, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bGhostbusters) }
        )
        binding.bSpellOnYou.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bSpellOnYou) },
            getPositionMs = { soundManager.getLongPosition(binding.bSpellOnYou) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bSpellOnYou, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bSpellOnYou) }
        )
        binding.bThisIsHalloween.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bThisIsHalloween) },
            getPositionMs = { soundManager.getLongPosition(binding.bThisIsHalloween) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bThisIsHalloween, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bThisIsHalloween) }
        )
        binding.bLittleShop.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bLittleShop) },
            getPositionMs = { soundManager.getLongPosition(binding.bLittleShop) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bLittleShop, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bLittleShop) }
        )
        binding.bAdamsFamily.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bAdamsFamily) },
            getPositionMs = { soundManager.getLongPosition(binding.bAdamsFamily) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bAdamsFamily, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bAdamsFamily) }
        )
        binding.bMonsterMash.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bMonsterMash) },
            getPositionMs = { soundManager.getLongPosition(binding.bMonsterMash) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bMonsterMash, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bMonsterMash) }
        )
        binding.bSkeletons.bind(
            getDurationMs = { soundManager.getLongDuration(binding.bSkeletons) },
            getPositionMs = { soundManager.getLongPosition(binding.bSkeletons) },
            seekToMs = { ms -> soundManager.seekLongTo(binding.bSkeletons, ms) },
            isPlaying = { soundManager.isLongPlaying(binding.bSkeletons) }
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        window.statusBarColor = getColor(R.color.colorAccent)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        binding.menuHamburger.setOnClickListener { view -> showPopupMenu(view) }

        binding.overlayImageView.setOnClickListener {
            val intent = Intent(this, SecretActivity::class.java)
            startActivity(intent)
        }

        // Button click listeners
        binding.bGhostbusters.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.ghostbusters, it)
            binding.bGhostbusters.onPlayStateChanged()
        }
        binding.bSpellOnYou.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.i_put_a_spell_on_you, it)
            binding.bSpellOnYou.onPlayStateChanged()
        }
        binding.bThisIsHalloween.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.this_is_halloween, it)
            binding.bThisIsHalloween.onPlayStateChanged()
        }
        binding.bLittleShop.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.little_shop_of_horrors, it)
            binding.bLittleShop.onPlayStateChanged()
        }
        binding.bAdamsFamily.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.adams_family, it)
            binding.bAdamsFamily.onPlayStateChanged()
        }
        binding.bMonsterMash.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.monster_mash, it)
            binding.bMonsterMash.onPlayStateChanged()
        }
        binding.bSkeletons.setOnClickListener {
            it.startAnimation(buttonAnimation)
            soundManager.playLongSound(this, R.raw.spooky_scary_skeletons, it)
            binding.bSkeletons.onPlayStateChanged()
        }
    }

    override fun getAllButtons(): List<TextView> = listOf(
        binding.bGhostbusters,
        binding.bSpellOnYou,
        binding.bThisIsHalloween,
        binding.bLittleShop,
        binding.bAdamsFamily,
        binding.bMonsterMash,
        binding.bSkeletons
    )

    override fun resetAllButtons() {
        soundManager.resetAllButtons(this, getAllButtons())
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
