package design.bosson.spookyhalloweensounds

import android.content.Context
import android.os.CountDownTimer
import java.util.Calendar

class CountdownManager(private val context: Context, private val updateCountdown: (String) -> Unit) {

    private var countDownTimer: CountDownTimer? = null

    fun startCountdown() {
        val halloweenDate = calculateHalloweenDate()
        val currentTime = Calendar.getInstance().timeInMillis
        val timeRemaining = halloweenDate - currentTime

        countDownTimer = object : CountDownTimer(timeRemaining, 3600000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdownText(millisUntilFinished)
            }

            override fun onFinish() {
                updateCountdown(context.getString(R.string.happy_halloween))
            }
        }.start()
    }

    private fun calculateHalloweenDate(): Long {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val today = Calendar.getInstance()

        // Set Halloween date for the current year
        val halloweenThisYear = Calendar.getInstance().apply {
            set(Calendar.YEAR, currentYear)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }

        // If today is after Halloween this year, calculate next year's Halloween
        return if (today.after(halloweenThisYear)) {
            // Set Halloween date for next year
            Calendar.getInstance().apply {
                set(Calendar.YEAR, currentYear + 1)
                set(Calendar.MONTH, Calendar.OCTOBER)
                set(Calendar.DAY_OF_MONTH, 31)
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
            }.timeInMillis
        } else {
            // Use this year's Halloween
            halloweenThisYear.timeInMillis
        }
    }

    private fun updateCountdownText(timeRemaining: Long) {
        val remainingDays = (timeRemaining / (1000 * 60 * 60 * 24)).toInt()

        val countdownText = when (remainingDays) {
            0 -> context.getString(R.string.happy_halloween)
            1 -> context.getString(R.string.tomorrow_is_halloween)
            else -> "$remainingDays days until Halloween"
        }
        updateCountdown(countdownText)
    }

    fun cancelCountdown() {
        countDownTimer?.cancel()
    }
}
