package design.bosson.spookyhalloweensounds

//import android.widget.Button
import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var soundManager: SoundManager

    /**
     * Abstract method to retrieve all buttons in the derived activities.
     */
    //protected abstract fun getAllButtons(): List<Button>
    protected abstract fun getAllButtons(): List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        soundManager = SoundManager()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundManager.releaseAllSounds(this)
    }

    override fun onPause() {
        super.onPause()

        // Obtain PowerManager to check screen state
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        val isScreenOn = powerManager.isInteractive  // For API >= 20

        if (isScreenOn) {
            // App is being backgrounded
            //soundManager.pauseAllSounds(this)
            //soundManager.resetAllButtons(this, getAllButtons())
        }
        // If screen is off, do not pause sounds or reset buttons
    }

    override fun onResume() {
        super.onResume()

        // Update button icons based on paused state
        val buttons = getAllButtons()
        buttons.forEach { button ->
            if (soundManager.isButtonPaused(button)) {
                soundManager.setPlayDrawable(this, button)  // Show play icon for paused buttons
                soundManager.setButtonColorPlaying(this, button)  // Keep colorButtonPressed
            }
        }
    }

    /**
     * Resets all buttons in derived activities.
     */
    protected abstract fun resetAllButtons()
}
