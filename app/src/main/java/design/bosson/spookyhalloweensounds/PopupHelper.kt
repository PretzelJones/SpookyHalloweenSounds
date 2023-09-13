package design.bosson.spookyhalloweensounds

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AlertDialog

class PopupHelper {
    companion object {
        private const val PREFS_NAME = "MyPreferences"
        private const val PREF_POPUP_DISPLAYED = "popupDisplayed"

        fun showPopupIfNeeded(context: Context): Boolean {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val popupDisplayed = sharedPreferences.getBoolean(PREF_POPUP_DISPLAYED, false)

            if (!popupDisplayed) {
                val dialog = AlertDialog.Builder(context, R.style.RoundedAlertDialog)
                    .setTitle(R.string.popup_title)
                    .setMessage(R.string.popup_text_pause)
                    .setPositiveButton(R.string.ok_button_text) { dialog, _ ->
                        sharedPreferences.edit().putBoolean(PREF_POPUP_DISPLAYED, true).apply()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                // Add animation effect
                val scaleX = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleX", 0.5f, 1f)
                val scaleY = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleY", 0.5f, 1f)
                val fadeIn = ObjectAnimator.ofFloat(dialog.window!!.decorView, "alpha", 0f, 1f)

                val animatorSet = AnimatorSet()
                animatorSet.playTogether(scaleX, scaleY, fadeIn)
                animatorSet.interpolator = AccelerateInterpolator()
                animatorSet.duration = 300

                dialog.setOnShowListener {
                    animatorSet.start()
                }
                dialog.show()
                return true // Indicate that the popup was shown
            }

            return false // Indicate that the popup was not shown
        }
    }
}

/*class PopupHelper {
    companion object {
        private const val PREFS_NAME = "MyPreferences"
        private const val PREF_POPUP_DISPLAYED = "popupDisplayed"

        fun showPopupIfNeeded(context: Context, popupDismissedCallback: () -> Unit) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val popupDisplayed = sharedPreferences.getBoolean(PREF_POPUP_DISPLAYED, false)

            if (!popupDisplayed) {
                val dialog = AlertDialog.Builder(context, R.style.RoundedAlertDialog)
                    .setTitle(R.string.popup_title)
                    .setMessage(R.string.popup_text_pause)
                    .setPositiveButton(R.string.ok_button_text) { dialog, _ ->
                        sharedPreferences.edit().putBoolean(PREF_POPUP_DISPLAYED, true).apply()
                        dialog.dismiss()
                        popupDismissedCallback.invoke()
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()
            }
        }

        fun showPopupIfNeeded(context: MovieActivity) {

        }
    }
}*/
