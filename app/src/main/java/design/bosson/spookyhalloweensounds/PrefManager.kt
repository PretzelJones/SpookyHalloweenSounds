package design.bosson.spookyhalloweensounds
// Used for the temporary message popup about restarting sounds.
// Called in Movie, Long, and Secret activities

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val PREF_NAME = "MyAppPreferences"
    private val KEY_FIRST_TIME_MAIN_ACTIVITY = "firstTimeMainActivity"

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun setFirstTimeMainActivity(isFirstTime: Boolean) {
        editor.putBoolean(KEY_FIRST_TIME_MAIN_ACTIVITY, isFirstTime)
        editor.apply()
    }

    fun isFirstTimeMainActivity(): Boolean {
        return pref.getBoolean(KEY_FIRST_TIME_MAIN_ACTIVITY, true)
    }
}