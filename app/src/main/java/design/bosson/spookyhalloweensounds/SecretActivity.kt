package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_secret.*
import kotlinx.android.synthetic.main.content_secret.*


class SecretActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        mp = MediaPlayer() //added to resolve NullPointerException
        val bTheOldTape = this.buttonTheOldTape
        val bTheGhostSong = this.buttonTheGhostSong
        val bChillingCries = this.buttonChillingCries
        val bCriesFromHell = this.buttonCriesFromHell

//        val oldPlayer = MediaPlayer.create(this@SecretActivity, R.raw.the_old_tape)
//        val ghostPlayer = MediaPlayer.create(this@SecretActivity, R.raw.the_ghost_song)
//        val chillingPlayer = MediaPlayer.create(this@SecretActivity, R.raw.chilling_cries)
//        val criesPlayer = MediaPlayer.create(this@SecretActivity, R.raw.cries_from_hell)

        //sets font for buttons
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bTheOldTape.typeface = mTypeFace
        bTheGhostSong.typeface = mTypeFace
        bChillingCries.typeface = mTypeFace
        bCriesFromHell.typeface = mTypeFace
        textDescription.typeface = mTypeFace


        //media player methods
        bTheOldTape.setOnClickListener {
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.the_old_tape)
            mPlay()
        }

        bTheGhostSong.setOnClickListener {
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.the_ghost_song)
            mPlay()
        }

        bChillingCries.setOnClickListener {
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.chilling_cries)
            mPlay()
        }

        bCriesFromHell.setOnClickListener {
            stopPlaying()
            mp = MediaPlayer.create(this@SecretActivity, R.raw.cries_from_hell)
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

//    public override fun onPause() {
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

        /*
        if (id == R.id.payment){
            val intent = Intent(this, PaymentActivity::class.java)
            this.startActivity(intent)

        } else if
                */
        if (id == R.id.action_settings) {
            val intent = Intent(this, DeveloperActivity::class.java)
            this.startActivity(intent)

        } else if (id == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
            startActivity(Intent.createChooser(sharingIntent, "Share via"))

        } else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }

        return true

    }
}