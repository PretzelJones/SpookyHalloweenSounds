package design.bosson.spookyhalloweensounds

import android.app.AlertDialog
import android.content.Context

class PopupHelper {
    companion object {
        private const val PREFS_NAME = "MyPreferences"
        private const val PREF_POPUP_DISPLAYED = "popupDisplayed"

        fun showPopupIfNeeded(context: Context) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val popupDisplayed = sharedPreferences.getBoolean(PREF_POPUP_DISPLAYED, false)

            if (!popupDisplayed) {
                AlertDialog.Builder(context)
                        .setTitle(R.string.popup_title)
                        .setMessage(R.string.popup_text)
                        .setPositiveButton("OK") { dialog, _ ->
                            sharedPreferences.edit().putBoolean(PREF_POPUP_DISPLAYED, true).apply()
                            dialog.dismiss()
                        }
                        .setCancelable(false)
                        .show()
            }
        }
    }
}

