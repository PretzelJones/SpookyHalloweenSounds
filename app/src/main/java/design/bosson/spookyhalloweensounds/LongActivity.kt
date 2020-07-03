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

        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        bTerrorMix.typeface = mTypeFace
        bHauntedMix.typeface = mTypeFace
        bLongMix.typeface = mTypeFace
        bSpaceTerror.typeface = mTypeFace
        bDontLetIn.typeface = mTypeFace

        //sound managers
        bTerrorMix.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.ultra_terror)
            if (mp!!.isPlaying) {
                mp!!.pause()
            } else {
                mp!!.start()
            }
            mp!!.isLooping = true
        }

        bHauntedMix.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.haunted_house)
            mediaPlay()
        }

        bLongMix.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.long_mix)
            mediaPlay()
        }

        bSpaceTerror.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.space_terror)
            mediaPlay()
        }

        bDontLetIn.setOnClickListener {
            onPause()
            mp = MediaPlayer.create(this@LongActivity, R.raw.dont_let_in)
            mediaPlay()
        }

    }

    private fun mediaPlay() {
        if (mp!!.isPlaying) {
            mp!!.pause()
        } else {
            mp!!.start()
        }
        mp!!.isLooping = true
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