package design.bosson.spookyhalloweensounds

/*
    override fun onPause() {
        super.onPause()
        AudioControl.pauseAudio(mediaPlayerList) // to mute audio use muteAudio and unmuteAudio(mediaPlayerList)
    }

    override fun onResume() {
        super.onResume()
        AudioControl.resumeAudio(mediaPlayerList)
    }
 */

import android.media.MediaPlayer

object AudioControl {

    private val mediaPlayersState = mutableMapOf<MediaPlayer, Boolean>()

    fun muteAudio(mediaPlayerList: List<MediaPlayer>) {
        mediaPlayerList.forEach { mediaPlayer ->
            mediaPlayer.setVolume(0f, 0f) // Set volume to 0 (muted)
        }
    }

    fun unmuteAudio(mediaPlayerList: List<MediaPlayer>) {
        mediaPlayerList.forEach { mediaPlayer ->
            mediaPlayer.setVolume(1.0f, 1.0f) // Set volume back to the original value
        }
    }

    fun pauseAudio(mediaPlayerList: List<MediaPlayer>) {
        mediaPlayerList.forEach { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayersState[mediaPlayer] = true
                mediaPlayer.pause()
            } else {
                mediaPlayersState[mediaPlayer] = false
            }
        }
    }

    fun resumeAudio(mediaPlayerList: List<MediaPlayer>) {
        mediaPlayerList.forEach { mediaPlayer ->
            if (mediaPlayersState[mediaPlayer] == true) {
                mediaPlayer.start()
            }
        }
    }
}

