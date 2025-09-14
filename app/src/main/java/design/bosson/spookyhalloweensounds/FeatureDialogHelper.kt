package design.bosson.spookyhalloweensounds

import android.app.Activity
import android.app.AlertDialog
import android.content.Context

object FeatureDialogHelper {

    private const val PREFS_NAME = "app_prefs"
    private const val KEY_FEATURE_DIALOG_SHOWN = "feature_dialog_shown"

    fun showFeatureDialogIfNeeded(activity: Activity) {
        val prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val alreadyShown = prefs.getBoolean(KEY_FEATURE_DIALOG_SHOWN, false)

        if (!alreadyShown) {
            AlertDialog.Builder(activity)
                .setTitle("New Feature!")
                .setMessage("Press and hold a button, then slide your finger to fast-forward or rewind the sound")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    prefs.edit().putBoolean(KEY_FEATURE_DIALOG_SHOWN, true).apply()
                }
                .setCancelable(false) // optional, forces user to tap OK
                .show()
        }
    }
}
