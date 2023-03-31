package design.bosson.spookyhalloweensounds

//import android.widget.Toolbar
//import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_scrolling.*
import java.util.*


class ScrollingActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private var halloweenDate = Calendar.getInstance().apply {
        set(Calendar.MONTH, Calendar.NOVEMBER)
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis

    //private lateinit var countdownTimer: CountDownTimer
    //private lateinit var textCountdown: TextView

    //var handler: Handler? = null

    private var mp: MediaPlayer? = null
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //textCountdown = findViewById(R.id.textCountdown)

        //countDownStart() //old code

        mp = MediaPlayer() //added to resolve NullPointerException 10/27/17

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        AppRater(this).show()

        // Set the target date to October 31 of the current year
        // Countdown to Halloween
        updateCountdown()
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown()
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

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
            mPlay()
        }

        bBlackCat.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.black_cat)
            mPlay()
        }

        bEvilMan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.evil_man)
//            mp!!.start()
            mPlay()
        }

        bCreakyDoor.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.creaky_door)
            mPlay()
        }

        bHorrorAmbience.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.horror_ambience)
            mPlay()
        }

        bMonsterGrowl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.monster_growl)
            mPlay()
        }

        bMonsterWalking.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.monster_walking)
            mPlay()
        }

        bScaryScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scary_scream)
            mPlay()
        }

        bSpookyChains.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.spooky_chains)
            mPlay()
        }

        bThunder.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.thunder)
            mPlay()
        }

        bVampireBat.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.vampire_bat)
            mPlay()
        }

        bZombie.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.zombie)
            mPlay()
        }

        bGhostBoo.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.ghost_boo)
            mPlay()
        }

        bWerewolfHowl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.werewolf_howl)
            mPlay()
        }

        bPoltergeistVoice.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.poltergeist_voice)
            mPlay()
        }

        bZombieCome.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.zombie_come)
            mPlay()
        }

        bCatScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.cat_scream)
            mPlay()
        }

        bWraithWail.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.wraith_wail)
            mPlay()
        }

        bSpookyOwl.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.spooky_owl)
            mPlay()
        }

        bChainedGhoul.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.chained_ghoul)
            mPlay()
        }

        bTerrifiedScream.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.terrified_scream)
            mPlay()
        }

        bHauntedOrgan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.haunted_organ)
            mPlay()
        }

        bScareCrow.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scarecrow)
            mPlay()
        }

        bBlowingWind.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.blowing_wind)
            mPlay()
        }

        bGhostlyWhisper.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.ghostly_whisper)
            mPlay()
        }

        bDraculaLaugh.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.dracula_laugh)
            mPlay()
        }

        bWolfCry.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.wolf_cry)
            mPlay()
        }

        bKnockKnock.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.knock_knock)
            mPlay()
        }

        bIgorGrumble.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.igor_grumble)
            mPlay()
        }

        bHorrorMovie.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.horror_film)
            mPlay()
        }

        bTwoBells.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.warning_bells)
            mPlay()
        }

        bPainfulMoan.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.painful_moan)
            mPlay()
        }

        bWitchesCauldron.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.bubbles)
            mPlay()
        }

        bGhostlyChildren.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.scary_nursery)
            mPlay()
        }

        bHauntedSwamp.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.haunted_swamp)
            mPlay()
        }

        bTorturedSouls.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.tortured_souls)
            mPlay()
        }

        bChillingHorn.setOnClickListener {

            mp = MediaPlayer.create(this@ScrollingActivity, R.raw.chilling_horn)
            mPlay()
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

    private fun updateCountdown() {
        val currentDate = Calendar.getInstance().timeInMillis
        val remainingDays = ((halloweenDate - currentDate) / (1000 * 60 * 60 * 24)).toInt()
        textCountdown.text = if (remainingDays > 0) {
            "$remainingDays days till Halloween"
        } else {
            "Happy Halloween"
        }
        if (remainingDays == 1) {
            textCountdown.text = "Tomorrow is Halloween"
        }
    }

    private fun resetTimer() {
        // Reset the timer for next year's Halloween
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val nextYear = currentYear + 1
        halloweenDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, nextYear)
            set(Calendar.MONTH, Calendar.NOVEMBER)
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown()
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()
    }

    private fun mPlay() {
        mp!!.start()
    }

    override fun onStop() {
        super.onStop()
        mp?.release()
        mp = null
    }

//    override fun onPause() {
//        super.onPause()
//        if (mp != null) {
//            mp!!.release()
//            mp = null
//        }
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
/*
    //@SuppressLint("SimpleDateFormat")
    private fun countDownStart() {
        handler = Handler()
        //Calendar.getInstance().get(Calendar.YEAR);
        //set event date//YYYY-MM-DD
        //val year = Calendar.getInstance().get(Calendar.YEAR);

        val runnable: Runnable = object : Runnable {
            override fun run() {
                val dateFormat = SimpleDateFormat(getString(R.string.dateFormat))
                val halloweenDate = dateFormat.parse(getString(R.string.halloweenDate))
                val halloweenTomorrow = dateFormat.parse(getString(R.string.halloweenTomorrow))
                val today = Date()
                val diff = (halloweenDate!!.time - today.time)
                val days = diff / (24 * 60 * 60 * 1000)
                handler!!.postDelayed(this, 1000)

                //textCountdown.setText(String.format((diff / (24 * 60 * 60 * 1000)).toString()))
                //textCountdown.setText(String.format(today.toString()))

                try {

                    if (today.before(halloweenDate)) {
                        textCountdown.setText("" + String.format("%01d", days) + " days till Halloween")
                        textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)

                    } else if (days.equals("%01d")) {
                        textCountdown.setText("Happy Halloween")
                        textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)

                        /*if (!today.equals(halloweenDate)) {
                        textCountdown.setText("Happy Halloween")
                        textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)*/

                        //} else  {
                        //  textCountdown.setText("" + String.format("%01d", days) + " days till Halloween")
                        //textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)

                        /*} else if (!today.equals(halloweenTomorrow)) {
                        textCountdown.setText("Halloween is Tomorrow")
                        textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)

                    } else
                        textCountdown.setText("Spooky Halloween")
                        textCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12F)*/
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
*/
