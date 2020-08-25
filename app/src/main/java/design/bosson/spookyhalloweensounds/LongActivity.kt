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
import kotlinx.android.synthetic.main.content_long.*

class LongActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        //initialize buttons
        val bTerrorMix = this.buttonTerrorMix
        val bHauntedMix = this.buttonHauntedMix
        val bLongMix = this.buttonLongMix
        val bSpaceTerror = this.buttonSpaceTerror
        val bDontLetIn = this.buttonDontLetIn

        val terrorPlayer = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
        val hauntedPlayer = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
        val longPlayer = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
        val spacePlayer = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
        val dontPlayer = MediaPlayer.create(this@LongActivity, R.raw.dont_let_in)

        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bTerrorMix.typeface = mTypeFace
        bHauntedMix.typeface = mTypeFace
        bLongMix.typeface = mTypeFace
        bSpaceTerror.typeface = mTypeFace
        bDontLetIn.typeface = mTypeFace

        //sound managers
        bTerrorMix.setOnClickListener {
//            onPause()
//            mp = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
//            if (mp!!.isPlaying) {
//                mp!!.pause()
//            } else {
//                mp!!.start()
//            }
//            mp!!.isLooping = true

            if(terrorPlayer!!.isPlaying){
                terrorPlayer.pause();
            } else {
                terrorPlayer.start();
                terrorPlayer.isLooping = true
                terrorPlayer.setScreenOnWhilePlaying(true)
            }
        }

        bHauntedMix.setOnClickListener {
//            onPause()
//            mp = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
//            mediaPlay()

            if(hauntedPlayer!!.isPlaying){
                hauntedPlayer.pause();
            } else {
                hauntedPlayer.start();
                hauntedPlayer.isLooping = true
                hauntedPlayer.setScreenOnWhilePlaying(true)
            }
        }

        bLongMix.setOnClickListener {
//            onPause()
//            mp = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
//            mediaPlay()

            if(longPlayer!!.isPlaying){
                longPlayer.pause();
            } else {
                longPlayer.start();
                longPlayer.isLooping = true
                longPlayer.setScreenOnWhilePlaying(true)
            }
        }

        bSpaceTerror.setOnClickListener {
//            onPause()
//            mp = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
//            mediaPlay()

            if(spacePlayer!!.isPlaying){
                spacePlayer.pause();
            } else {
                spacePlayer.start();
                spacePlayer.isLooping = true
                spacePlayer.setScreenOnWhilePlaying(true)
            }
        }

        bDontLetIn.setOnClickListener {
//            onPause()
//            mp = MediaPlayer.create(this@LongActivity, R.raw.dont_let_in)
//            mediaPlay()

            if(dontPlayer!!.isPlaying){
                dontPlayer.pause();
            } else {
                dontPlayer.start();
                dontPlayer.isLooping = true
                dontPlayer.setScreenOnWhilePlaying(true)
            }
        }

    }

//    private fun mediaPlay() {
//        if (mp!!.isPlaying) {
//            mp!!.pause()
//        } else {
//            mp!!.start()
//        }
//        mp!!.isLooping = true
//    }
//
//    public override fun onPause() {
//        if (mp != null) {
//            mp!!.release()
//        }
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