package design.bosson.spookyhalloweensounds

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_scrolling.*
import java.io.*
import java.util.*

class ScrollingActivity : AppCompatActivity() {

    private lateinit var bWitchLaugh: Button
    private lateinit var bBlackCat: Button
    private lateinit var bEvilMan: Button
    private lateinit var bCreakyDoor: Button
    private lateinit var bHorrorAmbience: Button
    private lateinit var bMonsterGrowl: Button
    private lateinit var bMonsterWalking: Button
    private lateinit var bScaryScream: Button
    private lateinit var bSpookyChains: Button
    private lateinit var bThunder: Button
    private lateinit var bVampireBat: Button
    private lateinit var bZombie: Button
    private lateinit var bGhostBoo: Button
    private lateinit var bWerewolfHowl: Button
    private lateinit var bPoltergeistVoice: Button
    private lateinit var bZombieCome: Button
    private lateinit var bCatScream: Button
    private lateinit var bWraithWail: Button
    private lateinit var bSpookyOwl: Button
    private lateinit var bChainedGhoul: Button
    private lateinit var bTerrifiedScream: Button
    private lateinit var bHauntedOrgan: Button
    private lateinit var bScareCrow: Button
    private lateinit var bBlowingWind: Button
    private lateinit var bLoopingMix: Button
    private lateinit var bMovieThemes: Button
    private lateinit var bGhostlyWhisper: Button
    private lateinit var bDraculaLaugh: Button
    private lateinit var bWolfCry: Button
    private lateinit var bKnockKnock: Button
    private lateinit var bIgorGrumble: Button
    private lateinit var bHorrorMovie: Button
    private lateinit var bTwoBells: Button
    private lateinit var bPainfulMoan: Button
    private lateinit var bWitchesCauldron: Button
    private lateinit var bGhostlyChildren: Button
    private lateinit var bHauntedSwamp: Button
    private lateinit var bTorturedSouls: Button
    private lateinit var bChillingHorn: Button
    //private val requestCodeWriteStorage = 1
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    //private var doubleBackToExitPressedOnce = false

    private lateinit var timer: CountDownTimer
    private var halloweenDate = Calendar.getInstance().apply {
        set(Calendar.MONTH, Calendar.NOVEMBER)
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // Check for storage permission and show pop-up dialog if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            PopupHelper.showPopupIfNeeded(this)
        }
        // Obtain the FirebaseAnalytics instance.
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

        bLoopingMix = findViewById(R.id.buttonLoopingMix)
        bMovieThemes = findViewById(R.id.buttonMovieThemes)
        bWitchLaugh = findViewById(R.id.buttonWitch)
        bBlackCat = findViewById(R.id.buttonBlackCat)
        bEvilMan = findViewById(R.id.buttonEvilMan)
        bCreakyDoor = findViewById(R.id.buttonCreakyDoor)
        bHorrorAmbience = findViewById(R.id.buttonHorrorAmbience)
        bMonsterGrowl = findViewById(R.id.buttonMonsterGrowl)
        bMonsterWalking = findViewById(R.id.buttonMonsterWalking)
        bScaryScream = findViewById(R.id.buttonScaryScream)
        bSpookyChains = findViewById(R.id.buttonSpookyChains)
        bThunder = findViewById(R.id.buttonThunder)
        bVampireBat = findViewById(R.id.buttonVampireBat)
        bZombie = findViewById(R.id.buttonZombie)
        bGhostBoo = findViewById(R.id.buttonGhostBoo)
        bWerewolfHowl = findViewById(R.id.buttonWerewolfHowl)
        bPoltergeistVoice = findViewById(R.id.buttonPoltergeistVoice)
        bZombieCome = findViewById(R.id.buttonZombieCome)
        bCatScream = findViewById(R.id.buttonCatScream)
        bWraithWail = findViewById(R.id.buttonWraithWail)
        bSpookyOwl = findViewById(R.id.buttonSpookyOwl)
        bChainedGhoul = findViewById(R.id.buttonChainedGhoul)
        bTerrifiedScream = findViewById(R.id.buttonTerrifiedScream)
        bHauntedOrgan = findViewById(R.id.buttonHauntedOrgan)
        bScareCrow = findViewById(R.id.buttonScareCrow)
        bBlowingWind = findViewById(R.id.buttonBlowingWind)
        bGhostlyWhisper = findViewById(R.id.buttonGhostlyWhisper)
        bDraculaLaugh = findViewById(R.id.buttonDraculaLaugh)
        bWolfCry = findViewById(R.id.buttonWolfCry)
        bKnockKnock = findViewById(R.id.buttonKnockKnock)
        bIgorGrumble = findViewById(R.id.buttonIgorGrumble)
        bHorrorMovie = findViewById(R.id.buttonHorrorMovie)
        bTwoBells = findViewById(R.id.buttonTwoBells)
        bPainfulMoan = findViewById(R.id.buttonPainfulMoans)
        bWitchesCauldron = findViewById(R.id.buttonWitchesCauldron)
        bGhostlyChildren = findViewById(R.id.buttonGhostlyChildren)
        bHauntedSwamp = findViewById(R.id.buttonHauntedSwamp)
        bTorturedSouls = findViewById(R.id.buttonTorturedSouls)
        bChillingHorn = findViewById(R.id.buttonChillingHorn)

        //media player sounds
        bLoopingMix.setOnClickListener {
            val intent = Intent(this, LongActivity::class.java)
            this.startActivity(intent)
        }

        bMovieThemes.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            this.startActivity(intent)
        }

        bWitchLaugh.setOnClickListener {
            playSound(R.raw.witch_laugh)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bWitchLaugh.setOnLongClickListener {
                saveRingtone("Witch Laugh", R.raw.witch_laugh)
                true
            }
        }

        bBlackCat.setOnClickListener {
            playSound(R.raw.black_cat)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bBlackCat.setOnLongClickListener {
                saveRingtone("Black Cat", R.raw.black_cat)
                true
            }
        }

        bEvilMan.setOnClickListener {
            playSound(R.raw.evil_man)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bEvilMan.setOnLongClickListener {
                saveRingtone("Evil Man", R.raw.evil_man)
                true
            }
        }

        bCreakyDoor.setOnClickListener {
            playSound(R.raw.creaky_door)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bCreakyDoor.setOnLongClickListener {
                saveRingtone("Creaky Door", R.raw.creaky_door)
                true
            }
        }

        bHorrorAmbience.setOnClickListener {
            playSound(R.raw.horror_ambience)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bHorrorAmbience.setOnLongClickListener {
                saveRingtone("Horror Ambience", R.raw.horror_ambience)
                true
            }
        }

        bMonsterGrowl.setOnClickListener {
            playSound(R.raw.monster_growl)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bMonsterGrowl.setOnLongClickListener {
                saveRingtone("Monster Growl", R.raw.monster_growl)
                true
            }
        }

        bMonsterWalking.setOnClickListener {
            playSound(R.raw.monster_walking)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bMonsterWalking.setOnLongClickListener {
                saveRingtone("Monster Walking", R.raw.monster_walking)
                true
            }
        }

        bScaryScream.setOnClickListener {
            playSound(R.raw.scary_scream)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bScaryScream.setOnLongClickListener {
                saveRingtone("Scary Scream", R.raw.scary_scream)
                true
            }
        }

        bSpookyChains.setOnClickListener {
            playSound(R.raw.spooky_chains)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bSpookyChains.setOnLongClickListener {
                saveRingtone("Spooky Chains", R.raw.spooky_chains)
                true
            }
        }

        bThunder.setOnClickListener {
            playSound(R.raw.thunder)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bThunder.setOnLongClickListener {
                saveRingtone("Thunder", R.raw.thunder)
                true
            }
        }

        bVampireBat.setOnClickListener {
            playSound(R.raw.vampire_bat)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bVampireBat.setOnLongClickListener {
                saveRingtone("Vampire Bat", R.raw.vampire_bat)
                true
            }
        }

        bZombie.setOnClickListener {
            playSound(R.raw.zombie)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bZombie.setOnLongClickListener {
                saveRingtone("Zombie", R.raw.zombie)
                true
            }
        }

        bGhostBoo.setOnClickListener {
            playSound(R.raw.ghost_boo)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bGhostBoo.setOnLongClickListener {
                saveRingtone("Ghost Boo", R.raw.ghost_boo)
                true
            }
        }

        bWerewolfHowl.setOnClickListener {
            playSound(R.raw.werewolf_howl)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bWerewolfHowl.setOnLongClickListener {
                saveRingtone("Werewolf Howl", R.raw.werewolf_howl)
                true
            }
        }

        bPoltergeistVoice.setOnClickListener {
            playSound(R.raw.poltergeist_voice)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bPoltergeistVoice.setOnLongClickListener {
                saveRingtone("Poltergeist Voice", R.raw.poltergeist_voice)
                true
            }
        }

        bZombieCome.setOnClickListener {
            playSound(R.raw.zombie_come)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bZombieCome.setOnLongClickListener {
                saveRingtone("Zombie Come", R.raw.zombie_come)
                true
            }
        }

        bCatScream.setOnClickListener {
            playSound(R.raw.cat_scream)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bCatScream.setOnLongClickListener {
                saveRingtone("Cat Scream", R.raw.cat_scream)
                true
            }
        }

        bWraithWail.setOnClickListener {
            playSound(R.raw.wraith_wail)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bWraithWail.setOnLongClickListener {
                saveRingtone("Wraith Wail", R.raw.wraith_wail)
                true
            }
        }

        bSpookyOwl.setOnClickListener {
            playSound(R.raw.spooky_owl)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bSpookyOwl.setOnLongClickListener {
                saveRingtone("Spooky Owl", R.raw.spooky_owl)
                true
            }
        }

        bChainedGhoul.setOnClickListener {
            playSound(R.raw.chained_ghoul)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bChainedGhoul.setOnLongClickListener {
                saveRingtone("Chained Ghoul", R.raw.chained_ghoul)
                true
            }
        }

        bTerrifiedScream.setOnClickListener {
            playSound(R.raw.terrified_scream)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bTerrifiedScream.setOnLongClickListener {
                saveRingtone("Terrified Scream", R.raw.terrified_scream)
                true
            }
        }

        bHauntedOrgan.setOnClickListener {
            playSound(R.raw.haunted_organ)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bHauntedOrgan.setOnLongClickListener {
                saveRingtone("Haunted Organ", R.raw.haunted_organ)
                true
            }
        }

        bScareCrow.setOnClickListener {
            playSound(R.raw.scarecrow)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bScareCrow.setOnLongClickListener {
                saveRingtone("Scarecrow", R.raw.scarecrow)
                true
            }
        }

        bBlowingWind.setOnClickListener {
            playSound(R.raw.blowing_wind)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bBlowingWind.setOnLongClickListener {
                saveRingtone("Blowing Wind", R.raw.blowing_wind)
                true
            }
        }

        bGhostlyWhisper.setOnClickListener {
            playSound(R.raw.ghostly_whisper)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bGhostlyWhisper.setOnLongClickListener {
                saveRingtone("Ghostly Whisper", R.raw.ghostly_whisper)
                true
            }
        }

        bDraculaLaugh.setOnClickListener {
            playSound(R.raw.dracula_laugh)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bDraculaLaugh.setOnLongClickListener {
                saveRingtone("Dracula Laugh", R.raw.dracula_laugh)
                true
            }
        }

        bWolfCry.setOnClickListener {
            playSound(R.raw.wolf_cry)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bWolfCry.setOnLongClickListener {
                saveRingtone("Wolf Cry", R.raw.wolf_cry)
                true
            }
        }

        bKnockKnock.setOnClickListener {
            playSound(R.raw.knock_knock)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bKnockKnock.setOnLongClickListener {
                saveRingtone("Knock Knock", R.raw.knock_knock)
                true
            }
        }

        bIgorGrumble.setOnClickListener {
            playSound(R.raw.igor_grumble)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bIgorGrumble.setOnLongClickListener {
                saveRingtone("Igor Grumble", R.raw.igor_grumble)
                true
            }
        }

        bHorrorMovie.setOnClickListener {
            playSound(R.raw.horror_film)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bHorrorMovie.setOnLongClickListener {
                saveRingtone("Horror Movie", R.raw.horror_film)
                true
            }
        }

        bTwoBells.setOnClickListener {
            playSound(R.raw.warning_bells)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bTwoBells.setOnLongClickListener {
                saveRingtone("Two Bells", R.raw.warning_bells)
                true
            }
        }

        bPainfulMoan.setOnClickListener {
            playSound(R.raw.painful_moan)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bPainfulMoan.setOnLongClickListener {
                saveRingtone("Painful Moan", R.raw.painful_moan)
                true
            }
        }

        bWitchesCauldron.setOnClickListener {
            playSound(R.raw.bubbles)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bWitchesCauldron.setOnLongClickListener {
                saveRingtone("Witches Cauldron", R.raw.bubbles)
                true
            }
        }

        bGhostlyChildren.setOnClickListener {
            playSound(R.raw.scary_nursery)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bGhostlyChildren.setOnLongClickListener {
                saveRingtone("Ghostly Children", R.raw.scary_nursery)
                true
            }
        }

        bHauntedSwamp.setOnClickListener {
            playSound(R.raw.haunted_swamp)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bHauntedSwamp.setOnLongClickListener {
                saveRingtone("Haunted Swamp", R.raw.haunted_swamp)
                true
            }
        }

        bTorturedSouls.setOnClickListener {
            playSound(R.raw.tortured_souls)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bTorturedSouls.setOnLongClickListener {
                saveRingtone("Tortured Souls", R.raw.tortured_souls)
                true
            }
        }

        bChillingHorn.setOnClickListener {
            playSound(R.raw.chilling_horn)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bChillingHorn.setOnLongClickListener {
                saveRingtone("Chilling Horn", R.raw.chilling_horn)
                true
            }
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
            textCountdown.text = getString(R.string.tomorrow)
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
/*
    private fun mPlay() {
        mp!!.start()
    }

    override fun onStop() {
        super.onStop()
        mp?.release()
        mp = null
    }
*/
//    override fun onPause() {
//        super.onPause()
//        if (mp != null) {
//            mp!!.release()
//            mp = null
//        }
//    }

    private fun playSound(soundId: Int) {
        val mediaPlayer = MediaPlayer.create(this, soundId)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
        mediaPlayer.start()
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
        when (id) {
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
            R.id.secret -> {
                val intent = Intent(this, SecretActivity::class.java)
                this.startActivity(intent)
            }
        }

        return true

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveRingtone(title: String, rawResourceId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            return
        }

        val resolver = contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "$title.mp3")
            put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_RINGTONES)
            put(MediaStore.Audio.Media.IS_RINGTONE, true)
            put(MediaStore.Audio.Media.IS_NOTIFICATION, false)
            put(MediaStore.Audio.Media.IS_ALARM, false)
            put(MediaStore.Audio.Media.IS_MUSIC, false)
        }
        var ringtoneUri: Uri? = null
        try {
            val ringtoneFileUri = resolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues)
                    ?: throw IOException("Failed to create new MediaStore record.")
            ringtoneUri = ringtoneFileUri
            val outputStream: OutputStream = (resolver.openOutputStream(ringtoneFileUri)
                    ?: throw IOException("Failed to get output stream.")) as FileOutputStream
            val inputStream: InputStream = resources.openRawResource(rawResourceId)
            val buffer = ByteArray(1024)
            var read: Int
            while (inputStream.read(buffer).also { read = it } > 0) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.close()
            inputStream.close()

            // Show a confirmation dialog to the user
            AlertDialog.Builder(this)
                    .setMessage("Ringtone saved.")
                    .setPositiveButton("OK", null)
                    .show()

        } catch (e: IOException) {
            Toast.makeText(this, "Error saving ringtone: ${e.message}", Toast.LENGTH_LONG).show()
            if (ringtoneUri != null) {
                resolver.delete(ringtoneUri, null, null)
            }
        }
    }
}





