package design.bosson.spookyhalloweensounds

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_secret.*

class SecretActivity : AppCompatActivity() {

    private lateinit var bTheOldTape: Button
    private lateinit var bTheGhostSong: Button
    private lateinit var bChillingCries: Button
    private lateinit var bCriesFromHell: Button

    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList_the_old_tape: BooleanArray
    private lateinit var isPlayingList_the_ghost_song: BooleanArray
    private lateinit var isPlayingList_chilling_cries: BooleanArray
    private lateinit var isPlayingList_cries_from_hell: BooleanArray

    // Initialize FirebaseAnalytics instance
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    //@SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        if (!PopupHelper.showPopupIfNeeded(this)) {
            val popupShown = PopupHelper.showPopupIfNeeded(this)
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        bTheOldTape = findViewById(R.id.buttonTheOldTape)
        bTheGhostSong = findViewById(R.id.buttonTheGhostSong)
        bChillingCries = findViewById(R.id.buttonChillingCries)
        bCriesFromHell = findViewById(R.id.buttonCriesFromHell)

        val the_old_tape = MediaPlayer.create(this, R.raw.the_old_tape)
        val the_ghost_song = MediaPlayer.create(this, R.raw.the_ghost_song)
        val chilling_cries = MediaPlayer.create(this, R.raw.chilling_cries)
        val cries_from_hell = MediaPlayer.create(this, R.raw.cries_from_hell)

        mediaPlayerList = mutableListOf(the_old_tape, the_ghost_song, chilling_cries, cries_from_hell)
        isPlayingList_the_old_tape = BooleanArray(1)
        isPlayingList_the_ghost_song = BooleanArray(1)
        isPlayingList_chilling_cries = BooleanArray(1)
        isPlayingList_cries_from_hell = BooleanArray(1)

        //play sound on button click
        bTheOldTape.setOnClickListener {
            val mediaPlayer = mediaPlayerList[0]
            val isPlaying = isPlayingList_the_old_tape[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_the_old_tape[0] = false
                bTheOldTape.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_the_old_tape[0] = true
                    bTheOldTape.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bTheOldTape.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[0]
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_the_old_tape[0] = false
                bTheOldTape.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_ghost_song), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bTheGhostSong.setOnClickListener {
            val mediaPlayer = mediaPlayerList[1]
            val isPlaying = isPlayingList_the_ghost_song[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_the_ghost_song[0] = false
                bTheGhostSong.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_the_ghost_song[0] = true
                    bTheGhostSong.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bTheGhostSong.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[1]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_the_ghost_song[0] = false
                mediaPlayer.pause()
                bTheGhostSong.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_old_tape), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bChillingCries.setOnClickListener {
            val mediaPlayer = mediaPlayerList[2]
            val isPlaying = isPlayingList_chilling_cries[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_chilling_cries[0] = false
                bChillingCries.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_chilling_cries[0] = true
                    bChillingCries.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bChillingCries.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[2]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_chilling_cries[0] = false
                mediaPlayer.pause()
                bChillingCries.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_dispair), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bCriesFromHell.setOnClickListener {
            val mediaPlayer = mediaPlayerList[3]
            val isPlaying = isPlayingList_cries_from_hell[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_cries_from_hell[0] = false
                bCriesFromHell.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_cries_from_hell[0] = true
                    bCriesFromHell.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bCriesFromHell.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[3]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_cries_from_hell[0] = false
                mediaPlayer.pause()
                bCriesFromHell.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_cries_hell), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }
    }
    // Release media player when switching between activities
    override fun onStop() {
        super.onStop()
        mediaPlayerList.forEach {
            it.release()
        }
    }
/*
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, ScrollingActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to prevent it from staying in the back stack
    }
*/
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
            }
            //R.id.secret -> {
            //    val intent = Intent(this, SecretActivity::class.java)
            //    this.startActivity(intent)
            //}
        }
        return true
    }
}

