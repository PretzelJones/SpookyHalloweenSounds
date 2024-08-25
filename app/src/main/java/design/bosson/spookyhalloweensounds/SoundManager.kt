package design.bosson.spookyhalloweensounds

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class SoundManager {
    private val mediaPlayerMap = mutableMapOf<View, MediaPlayer>()
    private val originalDrawableMap = mutableMapOf<Button, Drawable>()
    private var currentPlayingMediaPlayer: MediaPlayer? = null
    private var currentPlayingButton: Button? = null

    // Method for playing short sounds (like in ScrollingActivity)
    fun playShortSound(context: Context, soundId: Int, button: View) {
        try {
            val buttonWithDrawable = button as Button

            val existingMediaPlayer = mediaPlayerMap[buttonWithDrawable]

            if (existingMediaPlayer != null) {
                // If the sound is already playing, do nothing
                if (!existingMediaPlayer.isPlaying) {
                    existingMediaPlayer.start()
                    setButtonColorPlaying(context, buttonWithDrawable) // Set color when playing
                }
            } else {
                // Create and start a new MediaPlayer for short sound
                val mediaPlayer = MediaPlayer.create(context, soundId)

                mediaPlayer.setOnCompletionListener {
                    it.release()
                    mediaPlayerMap.remove(buttonWithDrawable)
                    resetButtonColor(context, buttonWithDrawable) // Reset button color when done
                }

                mediaPlayerMap[buttonWithDrawable] = mediaPlayer
                setButtonColorPlaying(context, buttonWithDrawable) // Set color to indicate playing
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Method for playing long sounds (like in MovieActivity)
    fun playLongSound(context: Context, soundId: Int, button: View) {
        try {
            // Cast the View to Button
            val buttonWithDrawable = button as Button

            // If a sound is already playing, handle pause/resume logic
            if (currentPlayingMediaPlayer != null && currentPlayingButton != null) {
                if (currentPlayingButton == buttonWithDrawable) {
                    if (currentPlayingMediaPlayer!!.isPlaying) {
                        // Pause the audio
                        currentPlayingMediaPlayer!!.pause()

                        // Revert the icon to the original drawable but keep the color changed
                        resetButtonDrawable(context, currentPlayingButton!!) // Revert to original drawable
                        setButtonColorPaused(context, currentPlayingButton!!) // Keep the color changed to indicate paused
                    } else {
                        // Resume the audio
                        currentPlayingMediaPlayer!!.start()

                        // Set the icon to ic_pause and keep the color changed
                        setPauseDrawable(context, currentPlayingButton!!) // Set pause drawable
                        setButtonColorPlaying(context, currentPlayingButton!!) // Keep the color changed to indicate playing
                    }
                    return
                } else {
                    // Release the current media player and reset the previous button's drawable and color
                    currentPlayingMediaPlayer!!.release()
                    resetButtonDrawable(context, currentPlayingButton!!)
                    resetButtonColor(context, currentPlayingButton!!) // Reset the color of the previous button
                    currentPlayingMediaPlayer = null
                }
            }

            // Store the original drawable if not already stored
            if (!originalDrawableMap.containsKey(buttonWithDrawable)) {
                val originalDrawable = buttonWithDrawable.compoundDrawables[1] // Top drawable
                originalDrawableMap[buttonWithDrawable] = originalDrawable
            }

            // Create a new MediaPlayer instance for long sound
            val mediaPlayer = MediaPlayer.create(context, soundId)

            mediaPlayer.setOnCompletionListener {
                it.release()
                resetButtonDrawable(context, buttonWithDrawable) // Revert to original drawable when sound finishes
                resetButtonColor(context, buttonWithDrawable) // Reset the button color when sound finishes
            }

            // Change the button color and icon to indicate sound is playing
            setButtonColorPlaying(context, buttonWithDrawable)
            setPauseDrawable(context, buttonWithDrawable) // Set pause drawable

            mediaPlayer.start()

            // Long press to restart the sound
            buttonWithDrawable.setOnLongClickListener {
                try {
                    mediaPlayer.seekTo(0)
                    mediaPlayer.start()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Unable to restart sound", Toast.LENGTH_SHORT).show()
                }
                true
            }

            // Set the current media player and button
            currentPlayingMediaPlayer = mediaPlayer
            currentPlayingButton = buttonWithDrawable

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Helper function to set button color when sound is paused (icon reverts to original)
    private fun setButtonColorPaused(context: Context, button: Button) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.colorButtonPressed) // Paused state color
        )
    }

    // Helper function to set button color when sound is playing
    private fun setButtonColorPlaying(context: Context, button: Button) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.colorButtonPressed) // Playing state color
        )
    }

    // Helper function to set the pause drawable on the button
    private fun setPauseDrawable(context: Context, button: Button) {
        val pauseDrawable = ContextCompat.getDrawable(context, R.drawable.ic_pause)
        pauseDrawable?.setTint(ContextCompat.getColor(context, android.R.color.black))
        button.setCompoundDrawablesWithIntrinsicBounds(null, pauseDrawable, null, null)
    }

    // Helper function to reset the button's drawable to its original state
    fun resetButtonDrawable(context: Context, button: Button) {
        val originalDrawable = originalDrawableMap[button]
        if (originalDrawable != null) {
            button.setCompoundDrawablesWithIntrinsicBounds(null, originalDrawable, null, null)
        }
    }

    // Helper to reset button color
    fun resetButtonColor(context: Context, button: Button) {
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, R.color.colorButton)
        )
    }

    fun releaseAllSounds() {
        // Release all media players
        mediaPlayerMap.values.forEach { mediaPlayer ->
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        mediaPlayerMap.clear()

        // Reset the current playing long sound if any
        if (currentPlayingMediaPlayer != null) {
            currentPlayingMediaPlayer!!.stop()
            currentPlayingMediaPlayer!!.release()
            currentPlayingMediaPlayer = null
            currentPlayingButton = null
        }
    }


}
