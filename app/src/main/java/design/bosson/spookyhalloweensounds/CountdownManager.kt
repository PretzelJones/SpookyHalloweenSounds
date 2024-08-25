package design.bosson.spookyhalloweensounds

import android.content.Context
import android.os.CountDownTimer
import java.util.Calendar

class CountdownManager(private val context: Context, private val updateCallback: (String) -> Unit) {

    private lateinit var timer: CountDownTimer
    private var halloweenDate: Long = 0

    fun startCountdown(halloweenDate: Long) {
        this.halloweenDate = halloweenDate
        timer = object : CountDownTimer(halloweenDate - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountdown(millisUntilFinished)
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()
    }

    private fun updateCountdown(timeRemaining: Long) {
        val remainingDays = (timeRemaining / (1000 * 60 * 60 * 24)).toInt()
        val text = when (remainingDays) {
            0 -> context.getString(R.string.happy_halloween)
            1 -> context.getString(R.string.tomorrow_is_halloween)
            else -> "$remainingDays days until Halloween"
        }
        updateCallback(text) // Call the UI update
    }

    private fun resetTimer() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val nextYear = currentYear + 1
        halloweenDate = calculateHalloweenDate(nextYear)
        startCountdown(halloweenDate)
    }

    private fun calculateHalloweenDate(year: Int): Long {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.HOUR_OF_DAY, 23)
        }
        return calendar.timeInMillis
    }
}
