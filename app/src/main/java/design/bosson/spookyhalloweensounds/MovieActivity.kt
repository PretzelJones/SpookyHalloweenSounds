package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_long.*
import kotlinx.android.synthetic.main.content_movie.*

class MovieActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        //initialize buttons
        val bHalloween = this.buttonHalloween
        val bExorcist = this.buttonExorcist
        val bShining = this.buttonShining
        val bElmStreet = this.buttonElmStreet
        val bFriday = this.buttonFriday

//        val halloweenPlayer = MediaPlayer.create(this@MovieActivity, R.raw.halloween)
//        val exorcistPlayer = MediaPlayer.create(this@MovieActivity, R.raw.exorcist)
//        val shiningPlayer = MediaPlayer.create(this@MovieActivity, R.raw.shining)
//        val elmPlayer = MediaPlayer.create(this@MovieActivity, R.raw.elm_street)
//        val fridayPlayer = MediaPlayer.create(this@MovieActivity, R.raw.vorhees)

        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bHalloween.typeface = mTypeFace
        bExorcist.typeface = mTypeFace
        bShining.typeface = mTypeFace
        bElmStreet.typeface = mTypeFace
        bFriday.typeface = mTypeFace


        //sound managers
        bHalloween.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.halloween)
            mp!!.start()
            mp!!.isLooping = true

//             fun onBackPressed() {
//                 halloweenPlayer!!.reset();
//                 halloweenPlayer.release();
//                super.onBackPressed();
//            }
//            if (mp!!.isPlaying) {
//                mp!!.pause();
//            } else {
//                mp!!.start();
//                mp!!.isLooping = true
//                mp!!.setScreenOnWhilePlaying(true)
//            }
        }

        bExorcist.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.exorcist)
            mp!!.start()
            mp!!.isLooping = true

//            if(exorcistPlayer!!.isPlaying){
//                exorcistPlayer.pause();
//            } else {
//                exorcistPlayer.start();
//                exorcistPlayer.isLooping = true
//                exorcistPlayer.setScreenOnWhilePlaying(true)
//            }

        }


        bShining.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.shining)
            mp!!.start()
            mp!!.isLooping = true

//            if(shiningPlayer!!.isPlaying){
//                shiningPlayer.pause();
//            } else {
//                shiningPlayer.start();
//                shiningPlayer.isLooping = true
//                shiningPlayer.setScreenOnWhilePlaying(true)
//            }
        }

        bElmStreet.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.elm_street)
            mp!!.start()
            mp!!.isLooping = true

//            if(elmPlayer!!.isPlaying){
//                elmPlayer.pause();
//            } else {
//                elmPlayer.start();
//                elmPlayer.isLooping = true
//                elmPlayer.setScreenOnWhilePlaying(true)
//            }
        }

        bFriday.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@MovieActivity, R.raw.vorhees)
            mp!!.start()
            mp!!.isLooping = true

//            if(fridayPlayer!!.isPlaying){
//                fridayPlayer.pause();
//            } else {
//                fridayPlayer.start();
//                fridayPlayer.isLooping = true
//                fridayPlayer.setScreenOnWhilePlaying(true)
//            }
        }
    }

    public override fun onPause() {
        if (mp != null) {
            mp!!.release()
        }

        super.onPause()
    }

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
