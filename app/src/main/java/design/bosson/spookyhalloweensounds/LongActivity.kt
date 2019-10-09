package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_long.*
import kotlinx.android.synthetic.main.content_long.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.content_secret.*

class LongActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //prevent title display

        //initialize buttons
        val bTerrorMix = this.buttonTerrorMix
        val bHauntedMix = this.buttonHauntedMix
        val bLongMix = this.buttonLongMix
        val bSpaceTerror = this.buttonSpaceTerror

        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bTerrorMix.typeface = mTypeFace
        bHauntedMix.typeface = mTypeFace
        bLongMix.typeface = mTypeFace
        bSpaceTerror.typeface = mTypeFace

        //sound managers
        bTerrorMix.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
            mp!!.start()
            mp!!.isLooping()
        }

        bHauntedMix.setOnClickListener {

            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
            mp!!.start()
            mp!!.isLooping = true
        }

        bLongMix.setOnClickListener {

            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
            mp!!.start()
            mp!!.isLooping = true
        }

        bSpaceTerror.setOnClickListener {

            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
            mp!!.start()
            mp!!.isLooping = true
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

        if (id == R.id.action_settings) {
            val intent = Intent(this, DeveloperActivity::class.java)
            this.startActivity(intent)

        } else if (id == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store
            ))
            startActivity(Intent.createChooser(sharingIntent, "Share via"))

        } else if (id == R.id.secret) {
            val intent = Intent(this, SecretActivity::class.java)
            this.startActivity(intent)
        }

        return true

    }
}

