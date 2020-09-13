package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_scrolling.*
import java.text.SimpleDateFormat
import java.util.*


class ScrollingActivity : AppCompatActivity() {

    public lateinit var textCountdown: TextView
    public val tvEvent: TextView? = null
    public var handler: Handler? = null

    private var mp: MediaPlayer? = null
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        textCountdown = findViewById(R.id.textCountdown)
        countDownStart()


        val manager = ReviewManagerFactory.create(this)

        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task: Task<ReviewInfo?> ->
            if (task.isSuccessful) {
                // We can get the ReviewInfo object
                val reviewInfo = task.result
                val flow = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { task: Task<Void?>? -> }
            } else {
                // There was some problem, continue regardless of the result.
            }
        }



//        fun textViewGone() {
//            findViewById(R.id.textView1).setVisibility(View.GONE)
//        }

        mp = MediaPlayer() //added to resolve NullPointerException 10/27/17

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        AppRater(this).show()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        //button initializers
        val bLoopingMix = this.buttonLoopingMix
        val bWitchLaugh = this.buttonWitch
        val bBlackCat = this.buttonBlackCat
        val bEvilMan = this.buttonEvilMan
        val bCreakyDoor = this.buttonCreakyDoor
        val bHorrorAmbience = this.buttonHorrorAmbience
        val bMonsterGrowl = this.buttonMonsterGrowl
        val bMonsterWalking = this.buttonMonsterWalking
        val bScaryScream = this.buttonScaryScream
        val bSpookyChains = this.buttonSpookyChains
        val bThunder = this.buttonThunder
        val bVampireBat = this.buttonVampireBat
        val bZombie = this.buttonZombie
        val bGhostBoo = this.buttonGhostBoo
        val bWerewolfHowl = this.buttonWerewolfHowl
        val bPoltergeistVoice = this.buttonPoltergeistVoice
        val bZombieCome = this.buttonZombieCome
        val bCatScream = this.buttonCatScream
        val bWraithWail = this.buttonWraithWail
        val bSpookyOwl = this.buttonSpookyOwl
        val bChainedGhoul = this.buttonChainedGhoul
        val bTerrifiedScream = this.buttonTerrifiedScream
        val bHauntedOrgan = this.buttonHauntedOrgan
        val bScareCrow = this.buttonScareCrow
        val bBlowingWind = this.buttonBlowingWind
        val bGhostlyWhisper = this.buttonGhostlyWhisper
        val bDraculaLaugh = this.buttonDraculaLaugh
        val bWolfCry = this.buttonWolfCry
        val bKnockKnock = this.buttonKnockKnock
        val bIgorGrumble = this.buttonIgorGrumble
        val bHorrorMovie = this.buttonHorrorMovie
        val bTwoBells = this.buttonTwoBells
        val bPainfulMoan = this.buttonPainfulMoans
        val bWitchesCauldron = this.buttonWitchesCauldron
        val bGhostlyChildren = this.buttonGhostlyChildren
        val bHauntedSwamp = this.buttonHauntedSwamp
        val bTorturedSouls = this.buttonTorturedSouls
        val bChillingHorn = this.buttonChillingHorn
        val bMovieThemes = this.buttonMovieThemes

        //sets font for buttons on API 16
        val mTypeFace = Typeface.createFromAsset(assets, "Creepster.ttf")

        //font
        bLoopingMix.typeface = mTypeFace
        bWitchLaugh.typeface = mTypeFace
        bBlackCat.typeface = mTypeFace
        bEvilMan.typeface = mTypeFace
        bCreakyDoor.typeface = mTypeFace
        bHorrorAmbience.typeface = mTypeFace
        bMonsterGrowl.typeface = mTypeFace
        bMonsterWalking.typeface = mTypeFace
        bScaryScream.typeface = mTypeFace
        bSpookyChains.typeface = mTypeFace
        bThunder.typeface = mTypeFace
        bVampireBat.typeface = mTypeFace
        bZombie.typeface = mTypeFace
        bGhostBoo.typeface = mTypeFace
        bWerewolfHowl.typeface = mTypeFace
        bPoltergeistVoice.typeface = mTypeFace
        bZombieCome.typeface = mTypeFace
        bCatScream.typeface = mTypeFace
        bWraithWail.typeface = mTypeFace
        bSpookyOwl.typeface = mTypeFace
        bChainedGhoul.typeface = mTypeFace
        bTerrifiedScream.typeface = mTypeFace
        bHauntedOrgan.typeface = mTypeFace
        bScareCrow.typeface = mTypeFace
        bBlowingWind.typeface = mTypeFace
        bGhostlyWhisper.typeface = mTypeFace
        bDraculaLaugh.typeface = mTypeFace
        bWolfCry.typeface = mTypeFace
        bKnockKnock.typeface = mTypeFace
        bIgorGrumble.typeface = mTypeFace
        bHorrorMovie.typeface = mTypeFace
        bTwoBells.typeface = mTypeFace
        bPainfulMoan.typeface = mTypeFace
        bWitchesCauldron.typeface = mTypeFace
        bGhostlyChildren.typeface = mTypeFace
        bHauntedSwamp.typeface = mTypeFace
        bTorturedSouls.typeface = mTypeFace
        bChillingHorn.typeface = mTypeFace
        bMovieThemes.typeface = mTypeFace

        //media player sounds
        buttonLoopingMix.setOnClickListener {
            val intent = Intent(this, LongActivity::class.java)
            this.startActivity(intent)
        }

        buttonMovieThemes.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            this.startActivity(intent)
        }

        bWitchLaugh.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.witch_laugh)
            mp!!.start()
        }

        bEvilMan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.evil_man)
            mp!!.start()
        }

        bCreakyDoor.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.creaky_door)
            mp!!.start()
        }

        bBlackCat.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.black_cat)
            mp!!.start()
        }

        bHorrorAmbience.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.horror_ambience)
            mp!!.start()
        }

        bMonsterGrowl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.monster_growl)
            mp!!.start()
        }

        bMonsterWalking.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.monster_walking)
            mp!!.start()
        }

        bScaryScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scary_scream)
            mp!!.start()
        }

        bSpookyChains.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.spooky_chains)
            mp!!.start()
        }

        bThunder.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.thunder)
            mp!!.start()
        }

        bVampireBat.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.vampire_bat)
            mp!!.start()
        }

        bZombie.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.zombie)
            mp!!.start()
        }

        bGhostBoo.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.ghost_boo)
            mp!!.start()
        }

        bWerewolfHowl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.werewolf_howl)
            mp!!.start()
        }

        bPoltergeistVoice.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.poltergeist_voice)
            mp!!.start()
        }

        bZombieCome.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.zombie_come)
            mp!!.start()
        }

        bCatScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.cat_scream)
            mp!!.start()
        }

        bWraithWail.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.wraith_wail)
            mp!!.start()
        }

        bSpookyOwl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.spooky_owl)
            mp!!.start()
        }

        bChainedGhoul.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.chained_ghoul)
            mp!!.start()
        }

        bTerrifiedScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.terrified_scream)
            mp!!.start()
        }

        bHauntedOrgan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.haunted_organ)
            mp!!.start()
        }

        bScareCrow.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scarecrow)
            mp!!.start()
        }

        bBlowingWind.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.blowing_wind)
            mp!!.start()
        }

        bGhostlyWhisper.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.ghostly_whisper)
            mp!!.start()
        }

        bDraculaLaugh.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.dracula_laugh)
            mp!!.start()
        }

        bWolfCry.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.wolf_cry)
            mp!!.start()
        }

        bKnockKnock.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.knock_knock)
            mp!!.start()
        }

        bIgorGrumble.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.igor_grumble)
            mp!!.start()
        }

        bHorrorMovie.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.horror_film)
            mp!!.start()
        }

        bTwoBells.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.warning_bells)
            mp!!.start()
        }

        bPainfulMoan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.painful_moan)
            mp!!.start()
        }

        bWitchesCauldron.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.bubbles)
            mp!!.start()
        }

        bGhostlyChildren.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scary_nursery)
            mp!!.start()
        }

        bHauntedSwamp.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.haunted_swamp)
            mp!!.start()
        }

        bTorturedSouls.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.tortured_souls)
            mp!!.start()
        }

        bChillingHorn.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.chilling_horn)
            mp!!.start()
        }

    }

    override fun onBackPressed() {

        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true

            //display msg
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        } else {

            super.onBackPressed()

        }
    }

    override fun onPause() {
        super.onPause()
        if (mp != null) {
            mp!!.release()
            mp = null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_scrolling, menu)

        return true
    }

    fun countDownStart() {
        handler = Handler()
        //                    Calendar.getInstance().get(Calendar.YEAR);
        //set event date//YYYY-MM-DD
        val runnable: Runnable = object : Runnable {
            override fun run() {
                handler!!.postDelayed(this, 1000)
                try {
//                    Calendar.getInstance().get(Calendar.YEAR);
                    val dateFormat = SimpleDateFormat(
                            "yyyy-MM-dd")
                    //set event date//YYYY-MM-DD
                    val futureDate = dateFormat.parse(getString(R.string.date))
                    val currentDate = Date()
                    if (!currentDate.after(futureDate)) {
                        assert(futureDate != null)
                        var diff = (futureDate!!.time
                                - currentDate.time)
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000
                        textCountdown.setText("" + String.format("%02d", days) + " days till Halloween")
                    } else {
//                            tvEvent!!.visibility = View.VISIBLE
//                            tvEvent.text = "The event started!"
//                            textViewGone()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler!!.postDelayed(runnable, 1000)
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
