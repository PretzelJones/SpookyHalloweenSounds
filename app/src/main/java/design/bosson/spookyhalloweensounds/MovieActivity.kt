package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_long.toolbar
import kotlinx.android.synthetic.main.content_movie.buttonAmityville
import kotlinx.android.synthetic.main.content_movie.buttonElmStreet
import kotlinx.android.synthetic.main.content_movie.buttonExorcist
import kotlinx.android.synthetic.main.content_movie.buttonFriday
import kotlinx.android.synthetic.main.content_movie.buttonHalloween
import kotlinx.android.synthetic.main.content_movie.buttonShining

open class MovieActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        //initialize buttons
        val bHalloween = this.buttonHalloween
        val bExorcist = this.buttonExorcist
        val bShining = this.buttonShining
        val bElmStreet = this.buttonElmStreet
        val bFriday = this.buttonFriday
        val bAmityville = this.buttonAmityville

        // initialize button animation for long and movie buttons
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)
        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bHalloween.typeface = mTypeFace
        bExorcist.typeface = mTypeFace
        bShining.typeface = mTypeFace
        bElmStreet.typeface = mTypeFace
        bFriday.typeface = mTypeFace
        bAmityville.typeface = mTypeFace

        //sound managers
        bHalloween.setOnClickListener {
            bHalloween.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.halloween)
            mPlay()
        }

        bExorcist.setOnClickListener {
            bExorcist.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.exorcist)
            mPlay()
        }

        bShining.setOnClickListener {
            bShining.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.shining)
            mPlay()
        }

        bElmStreet.setOnClickListener {
            bElmStreet.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.elm_street)
            mPlay()
        }

        bFriday.setOnClickListener {
            bFriday.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.vorhees)
            mPlay()
        }

        bAmityville.setOnClickListener {
            bAmityville.startAnimation(buttonAnimation)
            stopPlaying()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.amityville)
            mPlay()
        }
    }

    private fun mPlay () {
        mp!!.start()
        mp!!.isLooping = true
        mp!!.setScreenOnWhilePlaying(true)
    }

    private fun stopPlaying() {
        // If media player is not null then try to stop it
        if (mp != null) {
            mp!!.stop()
            mp!!.release()
            mp = null
        }
    }

    override fun onStop() {
        super.onStop()
        mp?.release()
        mp = null
    }

//    override fun onPause() {
//        if (mp != null) {
//            mp!!.release()
//        }
//
//        super.onPause()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_scrolling, menu)

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

        } /*else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }*/

        return true

    }
}

