package design.bosson.spookyhalloweensounds

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

class SoundManager {

    // Map to associate buttons with their corresponding MediaPlayers for long sounds
    private val longMediaPlayerMap = mutableMapOf<View, MediaPlayer>()

    // Map to associate buttons with their corresponding MediaPlayers for short sounds
    private val shortMediaPlayerMap = mutableMapOf<View, MediaPlayer>()

    // Map to store the original drawable of each button
    private val originalDrawableMap = mutableMapOf<Button, Drawable>()

    // Variables to track the currently playing MediaPlayer and its associated button for long sounds
    private var currentPlayingLongMediaPlayer: MediaPlayer? = null
    private var currentPlayingLongButton: Button? = null

    // Set to keep track of buttons whose long audio is paused
    private val pausedLongButtons = mutableSetOf<Button>()

    /**
     * Plays a long sound associated with a button.
     * Handles play, pause, and resume functionalities.
     */
    fun playLongSound(context: Context, soundId: Int, button: View) {
        try {
            val buttonWithDrawable = button as Button

            // If a long sound is already playing
            if (currentPlayingLongMediaPlayer != null && currentPlayingLongButton != null) {
                if (currentPlayingLongButton == buttonWithDrawable) {
                    if (currentPlayingLongMediaPlayer!!.isPlaying) {
                        // Pause the current long sound
                        currentPlayingLongMediaPlayer!!.pause()
                        pausedLongButtons.add(currentPlayingLongButton!!)
                        setPlayDrawable(context, currentPlayingLongButton!!)
                    } else {
                        // Resume the current long sound
                        currentPlayingLongMediaPlayer!!.start()
                        setPauseDrawable(context, currentPlayingLongButton!!)
                        pausedLongButtons.remove(currentPlayingLongButton!!)
                    }
                    return
                } else {
                    // If a different long sound was playing, stop it
                    currentPlayingLongMediaPlayer!!.release()
                    resetButtonDrawable(context, currentPlayingLongButton!!)
                    resetButtonColor(context, currentPlayingLongButton!!)
                    pausedLongButtons.remove(currentPlayingLongButton!!)
                    currentPlayingLongMediaPlayer = null
                }
            }

            // Store the original drawable if not already stored
            if (!originalDrawableMap.containsKey(buttonWithDrawable)) {
                val originalDrawable = buttonWithDrawable.compoundDrawables[1] // Top drawable
                originalDrawableMap[buttonWithDrawable] = originalDrawable
            }

            // Create a new MediaPlayer instance for the long sound
            val mediaPlayer = MediaPlayer.create(context, soundId)
            mediaPlayer.isLooping = true

            mediaPlayer.setOnCompletionListener {
                // Release MediaPlayer and reset button UI after playback
                it.release()
                resetButtonDrawable(context, buttonWithDrawable)
                resetButtonColor(context, buttonWithDrawable)
            }

            // Update button UI to indicate playing state
            setButtonColorPlaying(context, buttonWithDrawable)
            setPauseDrawable(context, buttonWithDrawable)

            mediaPlayer.start()

            // Handle long press to restart the sound
            buttonWithDrawable.setOnLongClickListener {
                try {
                    mediaPlayer.seekTo(0)  // Restart the sound from the beginning
                    mediaPlayer.start()    // Start playing again
                    setPauseDrawable(context, buttonWithDrawable)  // Ensure the pause icon is shown
                    setButtonColorPlaying(context, buttonWithDrawable)  // Keep colorButtonPressed
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
                true
            }

            // Update current playing references
            currentPlayingLongMediaPlayer = mediaPlayer
            currentPlayingLongButton = buttonWithDrawable

            // Map the MediaPlayer to the button
            longMediaPlayerMap[buttonWithDrawable] = mediaPlayer

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Plays a short sound associated with a button.
     * Handles single playback without looping.
     */
    fun playShortSound(context: Context, soundId: Int, button: View) {
        try {
            val buttonWithDrawable = button as Button

            // If a short sound is already playing on this button, restart it
            if (shortMediaPlayerMap.containsKey(buttonWithDrawable)) {
                val existingMediaPlayer = shortMediaPlayerMap[buttonWithDrawable]
                existingMediaPlayer?.release() // Stop the previous sound
                shortMediaPlayerMap.remove(buttonWithDrawable)
            }

            // Store the original drawable if not already stored
            if (!originalDrawableMap.containsKey(buttonWithDrawable)) {
                val originalDrawable = buttonWithDrawable.compoundDrawables[1] // Top drawable
                originalDrawableMap[buttonWithDrawable] = originalDrawable
            }

            // Create a new MediaPlayer instance for the short sound
            val mediaPlayer = MediaPlayer.create(context, soundId)

            mediaPlayer.setOnCompletionListener {
                // Release MediaPlayer and reset button UI after playback finishes
                it.release()
                shortMediaPlayerMap.remove(buttonWithDrawable)
                resetButtonColor(context, buttonWithDrawable) // Reset the button color
            }

            // Update button UI to indicate it's in a "playing" state
            setButtonColorPlaying(context, buttonWithDrawable)

            // Start playing the audio
            mediaPlayer.start()

            // Map the MediaPlayer to the button
            shortMediaPlayerMap[buttonWithDrawable] = mediaPlayer

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Resets all buttons based on their paused state for long sounds.
     * - Paused buttons: Set icon to play and keep colorButtonPressed.
     * - Other buttons: Reset to original drawable and color.
     */
    fun resetAllButtons(context: Context, buttons: List<Button>) {
        buttons.forEach { button ->
            if (pausedLongButtons.contains(button)) {
                setPlayDrawable(context, button)
                // Keep the color as colorButtonPressed
            } else {
                resetButtonDrawable(context, button)
                resetButtonColor(context, button)
            }
        }
    }

    /**
     * Resets the button's drawable to its original state.
     */
    fun resetButtonDrawable(context: Context, button: Button) {
        val originalDrawable = originalDrawableMap[button]
        if (originalDrawable != null) {
            button.setCompoundDrawablesWithIntrinsicBounds(null, originalDrawable, null, null)
        }
    }

    /**
     * Resets the button's color to its default state.
     */
    fun resetButtonColor(context: Context, button: Button) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.colorButton)
        )
    }

    /**
     * Sets the button's drawable to a pause icon.
     */
    fun setPauseDrawable(context: Context, button: Button) {
        val pauseDrawable = ContextCompat.getDrawable(context, R.drawable.ic_pause)
        pauseDrawable?.setTint(ContextCompat.getColor(context, android.R.color.black))
        button.setCompoundDrawablesWithIntrinsicBounds(null, pauseDrawable, null, null)
    }

    /**
     * Sets the button's drawable to a play icon.
     */
    fun setPlayDrawable(context: Context, button: Button) {
        val playDrawable = ContextCompat.getDrawable(context, R.drawable.ic_play)
        playDrawable?.setTint(ContextCompat.getColor(context, android.R.color.black))
        button.setCompoundDrawablesWithIntrinsicBounds(null, playDrawable, null, null)
    }

    /**
     * Sets the button color to indicate it's in an active state.
     */
    fun setButtonColorPlaying(context: Context, button: Button) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.colorButtonPressed) // Active state color
        )
    }

    /**
     * Checks if a specific long button's audio is paused.
     */
    fun isButtonPaused(button: Button): Boolean {
        return pausedLongButtons.contains(button)
    }

    /**
     * Checks if any long audio is currently playing.
     */
    /*
    fun isAnyAudioPlaying(): Boolean {
        return longMediaPlayerMap.values.any { it.isPlaying } || (currentPlayingLongMediaPlayer?.isPlaying
            ?: false)
    }

    /**
     * Checks if any long audio is currently paused.
     */
    fun isAnyAudioPaused(): Boolean {
        return pausedLongButtons.isNotEmpty()
    }
    */
    /**
     * Pauses all currently playing long sounds without releasing them.
     * Also updates the button UI to reflect the paused state.
     */
    fun pauseAllSounds(context: Context) {
        longMediaPlayerMap.forEach { (button, mediaPlayer) ->
            try {
                // Check if mediaPlayer is valid before calling isPlaying
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    pausedLongButtons.add(button as Button)
                    setPlayDrawable(context, button)
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                // Handle or log the exception appropriately
            }
        }

        currentPlayingLongMediaPlayer?.let {
            try {
                if (it.isPlaying) {
                    it.pause()
                    pausedLongButtons.add(currentPlayingLongButton!!)
                    setPlayDrawable(context, currentPlayingLongButton!!)
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                // Handle or log the exception appropriately
            }
        }
    }

    /**
    * Releases all MediaPlayer instances and stops all sounds.
    * Also updates the button UI to reflect the stopped state.
    */
    fun releaseAllSounds(context: Context) {
        // Release all long MediaPlayers
        longMediaPlayerMap.values.forEach { mediaPlayer ->
            try {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer.release()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                // Optionally log or handle the exception as needed
            }
        }
        longMediaPlayerMap.clear()
        pausedLongButtons.clear()
        currentPlayingLongMediaPlayer = null
        currentPlayingLongButton = null

        // Release all short MediaPlayers
        shortMediaPlayerMap.values.forEach { mediaPlayer ->
            try {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer.release()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                // Optionally log or handle the exception as needed
            }
        }
        shortMediaPlayerMap.clear()

        // Reset button visuals
        originalDrawableMap.keys.forEach { button ->
            resetButtonDrawable(context, button)
            resetButtonColor(context, button)
        }

        // Clear paused buttons
        pausedLongButtons.clear()
    }
}