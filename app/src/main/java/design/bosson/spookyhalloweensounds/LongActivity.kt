package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_long.*

@Suppress("NAME_SHADOWING")
class LongActivity : AppCompatActivity() {

    private lateinit var bTerrorMix: Button
    private lateinit var bHauntedMix: Button
    private lateinit var bLongMix: Button
    private lateinit var bSpaceTerror: Button
    private lateinit var bDontLetIn: Button

    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList_ultra_terror: BooleanArray
    private lateinit var isPlayingList_haunted_house: BooleanArray
    private lateinit var isPlayingList_long_mix: BooleanArray
    private lateinit var isPlayingList_space_terror: BooleanArray
    private lateinit var isPlayingList_dont_let_in: BooleanArray

    // Initialize FirebaseAnalytics instance
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        if (!PopupHelper.showPopupIfNeeded(this)) {
            val popupShown = PopupHelper.showPopupIfNeeded(this)
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        bTerrorMix = findViewById(R.id.buttonTerrorMix)
        bHauntedMix = findViewById(R.id.buttonHauntedMix)
        bLongMix = findViewById(R.id.buttonLongMix)
        bSpaceTerror = findViewById(R.id.buttonSpaceTerror)
        bDontLetIn = findViewById(R.id.buttonDontLetIn)

        val ultra_terror = MediaPlayer.create(this, R.raw.ultra_terror)
        val haunted_house = MediaPlayer.create(this, R.raw.haunted_house)
        val long_mix = MediaPlayer.create(this, R.raw.long_mix)
        val space_terror = MediaPlayer.create(this, R.raw.space_terror)
        val dont_let_in = MediaPlayer.create(this, R.raw.dont_let_in)

        mediaPlayerList =
            mutableListOf(ultra_terror, haunted_house, long_mix, space_terror, dont_let_in)
        isPlayingList_ultra_terror = BooleanArray(1)
        isPlayingList_haunted_house = BooleanArray(1)
        isPlayingList_long_mix = BooleanArray(1)
        isPlayingList_space_terror = BooleanArray(1)
        isPlayingList_dont_let_in = BooleanArray(1)

        //play sound on button click
        bTerrorMix.setOnClickListener {
            val mediaPlayer = mediaPlayerList[0]
            val isPlaying = isPlayingList_ultra_terror[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_ultra_terror[0] = false
                bTerrorMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_ultra_terror[0] = true
                    bTerrorMix.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bTerrorMix.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[0]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_ultra_terror[0] = false
                mediaPlayer.pause()
                bTerrorMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_ultra_terror), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }
        bHauntedMix.setOnClickListener {
            val mediaPlayer = mediaPlayerList[1]
            val isPlaying = isPlayingList_haunted_house[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_haunted_house[0] = false
                bHauntedMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_haunted_house[0] = true
                    bHauntedMix.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bHauntedMix.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[1]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_haunted_house[0] = false
                mediaPlayer.pause()
                bHauntedMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_haunted_circus), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bLongMix.setOnClickListener {
            val mediaPlayer = mediaPlayerList[2]
            val isPlaying = isPlayingList_long_mix[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_long_mix[0] = false
                bLongMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_long_mix[0] = true
                    bLongMix.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bLongMix.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[2]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_long_mix[0] = false
                mediaPlayer.pause()
                bLongMix.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_spooky_sounds), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bSpaceTerror.setOnClickListener {
            val mediaPlayer = mediaPlayerList[3]
            val isPlaying = isPlayingList_space_terror[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_space_terror[0] = false
                bSpaceTerror.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_space_terror[0] = true
                    bSpaceTerror.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bSpaceTerror.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[3]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_space_terror[0] = false
                mediaPlayer.pause()
                bSpaceTerror.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_space_terrors), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bDontLetIn.setOnClickListener {
            val mediaPlayer = mediaPlayerList[4]
            val isPlaying = isPlayingList_dont_let_in[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_dont_let_in[0] = false
                bDontLetIn.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_dont_let_in[0] = true
                    bDontLetIn.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bDontLetIn.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[4]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_dont_let_in[0] = false
                mediaPlayer.pause()
                bDontLetIn.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_key), null, null
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