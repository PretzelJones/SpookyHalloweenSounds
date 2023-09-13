package design.bosson.spookyhalloweensounds

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_long.toolbar

@Suppress("NAME_SHADOWING")
class MovieActivity : AppCompatActivity() {

    private lateinit var bHalloween: Button
    private lateinit var bExorcist: Button
    private lateinit var bShining: Button
    private lateinit var bElmStreet: Button
    private lateinit var bFriday: Button
    private lateinit var bAmityville: Button

    private lateinit var mediaPlayerList: MutableList<MediaPlayer>
    private lateinit var isPlayingList_halloween: BooleanArray
    private lateinit var isPlayingList_exorcist: BooleanArray
    private lateinit var isPlayingList_shining: BooleanArray
    private lateinit var isPlayingList_elmstreet: BooleanArray
    private lateinit var isPlayingList_friday: BooleanArray
    private lateinit var isPlayingList_amityville: BooleanArray

    // Initialize FirebaseAnalytics instance
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    //@SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        if (!PopupHelper.showPopupIfNeeded(this)) {
            val popupShown = PopupHelper.showPopupIfNeeded(this)
        }
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        bHalloween = findViewById(R.id.buttonHalloween)
        bExorcist = findViewById(R.id.buttonExorcist)
        bShining = findViewById(R.id.buttonShining)
        bElmStreet = findViewById(R.id.buttonElmStreet)
        bFriday = findViewById(R.id.buttonFriday)
        bAmityville = findViewById(R.id.buttonAmityville)

        val halloween = MediaPlayer.create(this, R.raw.halloween)
        val exorcist = MediaPlayer.create(this, R.raw.exorcist)
        val shining = MediaPlayer.create(this, R.raw.shining)
        val elmstreet = MediaPlayer.create(this, R.raw.elm_street)
        val friday = MediaPlayer.create(this, R.raw.vorhees)
        val amityville = MediaPlayer.create(this, R.raw.amityville)

        mediaPlayerList = mutableListOf(halloween, exorcist, shining, elmstreet, friday, amityville)
        isPlayingList_halloween = BooleanArray(1)
        isPlayingList_exorcist = BooleanArray(1)
        isPlayingList_shining = BooleanArray(1)
        isPlayingList_elmstreet = BooleanArray(1)
        isPlayingList_friday = BooleanArray(1)
        isPlayingList_amityville = BooleanArray(1)

        //play sound on button click

        bHalloween.setOnClickListener {
            // Get the MediaPlayer instance from mediaPlayerList at index 0
            val mediaPlayer = mediaPlayerList[0]
            val isPlaying = isPlayingList_halloween[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_halloween[0] = false
                bHalloween.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()

                    isPlayingList_halloween[0] = true
                    bHalloween.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bHalloween.setOnLongClickListener {

                val mediaPlayer = mediaPlayerList[0]

                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0)
                isPlayingList_halloween[0] = false
                mediaPlayer.pause()
                bHalloween.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_halloween), null, null
                )
                true
            }
        }

        bExorcist.setOnClickListener {
            val mediaPlayer = mediaPlayerList[1]
            val isPlaying = isPlayingList_exorcist[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_exorcist[0] = false
                bExorcist.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_exorcist[0] = true
                    bExorcist.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bExorcist.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[1]

                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_exorcist[0] = false
                mediaPlayer.pause()
                bExorcist.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_cross), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bShining.setOnClickListener {
            val mediaPlayer = mediaPlayerList[2]
            val isPlaying = isPlayingList_shining[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_shining[0] = false
                bShining.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_shining[0] = true
                    bShining.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bShining.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[2]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_shining[0] = false
                mediaPlayer.pause()
                bShining.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_axe), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bElmStreet.setOnClickListener {
            val mediaPlayer = mediaPlayerList[3]
            val isPlaying = isPlayingList_elmstreet[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_elmstreet[0] = false
                bElmStreet.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_elmstreet[0] = true
                    bElmStreet.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bElmStreet.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[3]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_elmstreet[0] = false
                mediaPlayer.pause()
                bElmStreet.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_krueger), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bFriday.setOnClickListener {
            val mediaPlayer = mediaPlayerList[4]
            val isPlaying = isPlayingList_friday[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_friday[0] = false
                bFriday.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_friday[0] = true
                    bFriday.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bFriday.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[4]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_friday[0] = false
                mediaPlayer.pause()
                bFriday.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_vorhees), null, null
                )
                true // Return true to indicate that the long click event is consumed
            }
        }

        bAmityville.setOnClickListener {
            val mediaPlayer = mediaPlayerList[5]
            val isPlaying = isPlayingList_amityville[0]

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlayingList_amityville[0] = false
                bAmityville.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_play), null, null
                )
            } else {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlayingList_amityville[0] = true
                    bAmityville.setCompoundDrawablesWithIntrinsicBounds(
                        null, resources.getDrawable(R.drawable.ic_pause), null, null
                    )
                }
            }
            bAmityville.setOnLongClickListener {
                val mediaPlayer = mediaPlayerList[5]
                //if (mediaPlayer.isPlaying) {
                //    mediaPlayer.pause()
                //}
                mediaPlayer.seekTo(0) // Restart from the beginning
                isPlayingList_amityville[0] = false
                mediaPlayer.pause()
                bAmityville.setCompoundDrawablesWithIntrinsicBounds(
                    null, resources.getDrawable(R.drawable.ic_amityville), null, null
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





